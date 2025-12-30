package org.bakhtaoui.team.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.application.service.TeamService;
import org.bakhtaoui.team.infrastructure.web.dto.GetTeamsResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public GetTeamsResponseDto fetchTeams() {
        return GetTeamsResponseDto.builder()
                .teams(teamService.fetchAllTeams().stream().toList())
                .build();
    }
}
