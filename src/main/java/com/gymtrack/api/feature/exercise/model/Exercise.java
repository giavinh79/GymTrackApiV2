package com.gymtrack.api.feature.exercise.model;

import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    // this is really just the "default" exercise value type for the exercise if there should be one (i.e. weight)
    private Integer exerciseValueTypeId;

    private Integer creatorId;

    private Integer imageId;

    @OneToMany(mappedBy = "exercise")
    private List<RoutineExercise> routineExercises;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id.equals(exercise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
