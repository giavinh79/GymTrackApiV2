package com.gymtrack.api.feature.set.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_reps", nullable = false)
    private Integer numReps;

    private Integer value;

    @Column(name = "exercise_value_type_id", nullable = false)
    private Integer exerciseValueTypeId;

    private Integer exerciseValueTypeUnitId;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return id.equals(set.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

