package org.bakhtaoui.team.application.helper;

import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.domain.port.TeamRepository;

import java.util.*;

public class InMemoryTeamRepository implements TeamRepository {

    private final Map<Long, Team> storage = new HashMap<>();

    public void save(Team team) {
        storage.put(team.id().value(), team);
    }

    @Override
    public List<Team> findAll() {
        return new ArrayList<>(storage.values());
    }
}

