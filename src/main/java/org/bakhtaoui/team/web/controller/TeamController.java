package org.bakhtaoui.team.web.controller;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.application.usecase.GetAllTeamsUseCase;
import org.bakhtaoui.team.web.dto.TeamResponseDto;
import org.bakhtaoui.team.web.mapper.TeamWebMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final GetAllTeamsUseCase getAllTeamsUseCase;
    private final TeamWebMapper mapper;

    @GetMapping
    public List<TeamResponseDto> getTeams() {
        return getAllTeamsUseCase.execute()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
