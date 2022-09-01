package com.gymtrack.api.feature.routine_exercise.model;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine.model.Routine;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "routine_exercise")
public class RoutineExercise {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer exerciseOrder;

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
