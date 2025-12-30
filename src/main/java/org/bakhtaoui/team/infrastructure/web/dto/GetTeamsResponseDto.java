package org.bakhtaoui.team.infrastructure.web.dto;

import lombok.Builder;
import org.bakhtaoui.team.application.dto.TeamDto;

import java.util.List;

@Builder
public record GetTeamsResponseDto(
        List<TeamDto> teams
) {
}
