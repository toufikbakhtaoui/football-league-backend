package org.bakhtaoui.team.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;

@Entity
@Table(name = "team")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {

    @Id
    private Long id;

    private String name;
    private String city;
    private String stadium;

    @Enumerated(EnumType.STRING)
    private Conference conference;

    @Enumerated(EnumType.STRING)
    private Division division;
}
