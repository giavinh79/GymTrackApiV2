package com.gymtrack.api.feature.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise_value_type.model.ExerciseValueType;
import com.gymtrack.api.feature.image.model.Image;
import com.gymtrack.api.feature.muscle.model.Muscle;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    // this is really just the "default" exercise value type for the exercise if there should be one (i.e. weight)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_value_type_id", referencedColumnName = "id")
    private ExerciseValueType exerciseValueType;

    private Integer creatorId;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise")
    private List<RoutineExercise> routineExercises;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "exercise_muscle",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id"))
    private List<Muscle> musclesUsed;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;


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
