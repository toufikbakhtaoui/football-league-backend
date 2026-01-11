package org.bakhtaoui.team.infrastructure.persistence.repository;

import org.bakhtaoui.team.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTeamJpaRepository extends JpaRepository<TeamEntity, Long> {
}
