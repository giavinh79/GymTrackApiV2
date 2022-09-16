package com.gymtrack.api.feature.exercise_value_type_unit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise_value_type.model.ExerciseValueType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExerciseValueTypeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(table = "exercise_value_type_unit", name = "exercise_value_type_id", nullable = false)
    private ExerciseValueType exerciseValueType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseValueTypeUnit exerciseValueTypeUnit = (ExerciseValueTypeUnit) o;
        return Objects.equals(id, exerciseValueTypeUnit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
