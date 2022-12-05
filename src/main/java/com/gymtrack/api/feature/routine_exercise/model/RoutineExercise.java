package com.gymtrack.api.feature.routine_exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.set.model.Set;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "routine_exercise")
public class RoutineExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer exerciseOrder;

    @Builder.Default
    @OneToMany
    @JoinTable(
            name = "routine_exercise_set",
            joinColumns = @JoinColumn(name = "routine_exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "set_id")
    )
    private List<Set> sets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 9)
    private Day day;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineExercise that = (RoutineExercise) o;
        return id.equals(that.id);
    }
}
