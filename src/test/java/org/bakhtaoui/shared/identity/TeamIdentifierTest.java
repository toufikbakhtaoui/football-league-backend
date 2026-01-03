package org.bakhtaoui.shared.identity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TeamIdentifierTest {

    @Test
    void should_create_team_identifier() {
        var teamIdentifier = TeamIdentifier.builder().value(1L).build();

        assertThat(teamIdentifier.value()).isEqualTo(1L);
    }

    @Test
    void should_throw_exception_when_value_is_not_present() {
        assertThatThrownBy(() ->
                new TeamIdentifier(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
