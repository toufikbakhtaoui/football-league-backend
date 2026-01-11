package org.bakhtaoui.team.infrastructure.persistence.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.team.infrastructure.persistence.entity.TeamEntity;
import org.bakhtaoui.team.infrastructure.persistence.repository.SpringTeamJpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamDataInitializer implements CommandLineRunner {

    private final SpringTeamJpaRepository repository;

    @Override
    public void run(String @NonNull ... args) {
        teams().forEach(this::upsert);

        long count = repository.count();
        if (count != 32) {
            throw new IllegalStateException(
                    "NFL seed corrupted: expected 32 teams but found " + count
            );
        }
        log.info("NFL teams seed completed");
    }

    private void upsert(TeamEntity incoming) {
        repository.findById(incoming.getId())
                .ifPresentOrElse(existing -> update(existing, incoming),
                    () -> repository.save(incoming));
    }

    private List<TeamEntity> teams() {
        return List.of(

                // ===== AFC EAST =====
                team(1L, "Bills", "BUF", "Buffalo", "Highmark Stadium", Conference.AFC, Division.EAST),
                team(2L, "Dolphins", "MIA", "Miami", "Hard Rock Stadium", Conference.AFC, Division.EAST),
                team(3L, "Patriots", "NE", "New England", "Gillette Stadium", Conference.AFC, Division.EAST),
                team(4L, "Jets", "NYJ", "New York", "MetLife Stadium", Conference.AFC, Division.EAST),

                // ===== AFC NORTH =====
                team(5L, "Ravens", "BAL", "Baltimore", "M&T Bank Stadium", Conference.AFC, Division.NORTH),
                team(6L, "Bengals", "CIN", "Cincinnati", "Paycor Stadium", Conference.AFC, Division.NORTH),
                team(7L, "Browns", "CLE", "Cleveland", "Cleveland Browns Stadium", Conference.AFC, Division.NORTH),
                team(8L, "Steelers", "PIT", "Pittsburgh", "Acrisure Stadium", Conference.AFC, Division.NORTH),

                // ===== AFC SOUTH =====
                team(9L, "Texans", "HOU", "Houston", "NRG Stadium", Conference.AFC, Division.SOUTH),
                team(10L, "Colts", "IND", "Indianapolis", "Lucas Oil Stadium", Conference.AFC, Division.SOUTH),
                team(11L, "Jaguars", "JAX", "Jacksonville", "EverBank Stadium", Conference.AFC, Division.SOUTH),
                team(12L, "Titans", "TEN", "Tennessee", "Nissan Stadium", Conference.AFC, Division.SOUTH),

                // ===== AFC WEST =====
                team(13L, "Broncos", "DEN", "Denver", "Empower Field at Mile High", Conference.AFC, Division.WEST),
                team(14L, "Chiefs", "KC", "Kansas City", "Arrowhead Stadium", Conference.AFC, Division.WEST),
                team(15L, "Raiders", "LV", "Las Vegas", "Allegiant Stadium", Conference.AFC, Division.WEST),
                team(16L, "Chargers", "LAC", "Los Angeles", "SoFi Stadium", Conference.AFC, Division.WEST),

                // ===== NFC EAST =====
                team(17L, "Cowboys", "DAL", "Dallas", "AT&T Stadium", Conference.NFC, Division.EAST),
                team(18L, "Giants", "NYG", "New York", "MetLife Stadium", Conference.NFC, Division.EAST),
                team(19L, "Eagles", "PHI", "Philadelphia", "Lincoln Financial Field", Conference.NFC, Division.EAST),
                team(20L, "Commanders", "WAS", "Washington", "FedExField", Conference.NFC, Division.EAST),

                // ===== NFC NORTH =====
                team(21L, "Bears", "CHI", "Chicago", "Soldier Field", Conference.NFC, Division.NORTH),
                team(22L, "Lions", "DET", "Detroit", "Ford Field", Conference.NFC, Division.NORTH),
                team(23L, "Packers", "GB", "Green Bay", "Lambeau Field", Conference.NFC, Division.NORTH),
                team(24L, "Vikings", "MIN", "Minnesota", "U.S. Bank Stadium", Conference.NFC, Division.NORTH),

                // ===== NFC SOUTH =====
                team(25L, "Falcons", "ATL", "Atlanta", "Mercedes-Benz Stadium", Conference.NFC, Division.SOUTH),
                team(26L, "Panthers", "CAR", "Carolina", "Bank of America Stadium", Conference.NFC, Division.SOUTH),
                team(27L, "Saints", "NO", "New Orleans", "Caesars Superdome", Conference.NFC, Division.SOUTH),
                team(28L, "Buccaneers", "TB", "Tampa Bay", "Raymond James Stadium", Conference.NFC, Division.SOUTH),

                // ===== NFC WEST =====
                team(29L, "Cardinals", "ARI", "Arizona", "State Farm Stadium", Conference.NFC, Division.WEST),
                team(30L, "Rams", "LAR", "Los Angeles", "SoFi Stadium", Conference.NFC, Division.WEST),
                team(31L, "49ers", "SF", "San Fransisco", "Levi's Stadium", Conference.NFC, Division.WEST),
                team(32L, "Seahawks", "SEA", "Seattle", "Lumen Field", Conference.NFC, Division.WEST)
        );
    }

    private void update(TeamEntity existing, TeamEntity incoming) {
        existing.setName(incoming.getName());
        existing.setCode(incoming.getCode());
        existing.setCity(incoming.getCity());
        existing.setStadium(incoming.getStadium());
        existing.setConference(incoming.getConference());
        existing.setDivision(incoming.getDivision());
        repository.save(existing);
    }

    private TeamEntity team(
            Long id,
            String name,
            String code,
            String city,
            String stadium,
            Conference conference,
            Division division
    ) {
        return new TeamEntity(id, name, code, city, stadium, conference, division);
    }
}
