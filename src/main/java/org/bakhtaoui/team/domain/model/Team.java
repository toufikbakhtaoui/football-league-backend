package org.bakhtaoui.team.domain.model;

import lombok.Builder;
import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;

@Builder
public record Team(
        TeamIdentifier id,
        String name,
        String city,
        String stadium,
        Conference conference,
        Division division
) {
}
