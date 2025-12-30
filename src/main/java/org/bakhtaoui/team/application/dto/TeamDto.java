package org.bakhtaoui.team.application.dto;

import lombok.Builder;
import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;

@Builder
public record TeamDto(
        Long id,
        String name,
        String city,
        String stadium,
        Conference conference,
        Division division
) {
}
