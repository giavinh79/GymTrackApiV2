package com.gymtrack.api.feature.exercise_value_type.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise_value_type_unit.model.ExerciseValueTypeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "exercise_value_type")
public class ExerciseValueType implements Serializable {
    @Serial
    private static final long serialVersionUID = 4068231476860236282L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "exerciseValueType", fetch = FetchType.EAGER)
    private List<ExerciseValueTypeUnit> exerciseValueTypeUnits;

    @JsonIgnore
    @OneToMany(mappedBy = "exerciseValueType")
    private transient List<Exercise> exercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseValueType exerciseValueType = (ExerciseValueType) o;
        return Objects.equals(id, exerciseValueType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
