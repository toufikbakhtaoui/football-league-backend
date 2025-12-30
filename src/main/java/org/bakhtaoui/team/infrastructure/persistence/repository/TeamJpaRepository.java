package org.bakhtaoui.team.infrastructure.persistence.repository;

import org.bakhtaoui.team.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity, Long> {
}
