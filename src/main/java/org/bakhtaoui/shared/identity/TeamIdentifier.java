package org.bakhtaoui.shared.identity;

import lombok.Builder;

@Builder
public record TeamIdentifier(
        Long value
) {
    public TeamIdentifier {
        if (value == null) {
            throw new IllegalArgumentException("TeamIdentifier cannot be null");
        }
    }
}
