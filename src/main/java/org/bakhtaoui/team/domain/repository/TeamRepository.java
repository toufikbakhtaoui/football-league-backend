package org.bakhtaoui.team.domain.repository;

import org.bakhtaoui.team.domain.model.Team;

import java.util.List;

public interface TeamRepository {

    List<Team> findAll();
}
