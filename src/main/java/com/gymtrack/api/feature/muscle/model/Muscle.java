package com.gymtrack.api.feature.muscle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise.model.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Muscle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "musclesUsed")
    private List<Exercise> exercisesApplicable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Muscle muscle = (Muscle) o;
        return Objects.equals(id, muscle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
