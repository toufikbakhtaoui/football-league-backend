package org.bakhtaoui.team.domain.view;

import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.shared.identity.TeamIdentifier;

public record TeamFixtureView(
        TeamIdentifier id,
        Conference conference,
        Division division
) {
}
