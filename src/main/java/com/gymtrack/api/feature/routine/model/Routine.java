package com.gymtrack.api.feature.routine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "public_id", length = 100, unique = true)
    private String publicId;

    @Column(name = "creator_id", nullable = false)
    private Integer creatorId;

    @Column(name = "image_id")
    private Integer imageId;

    private String name;
    private String description;

    private Double rating;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Column(name = "num_times_copied", nullable = false, columnDefinition = "integer default 0")
    private Integer numTimesCopied;

    @Column(name = "base_routine_id")
    private Integer baseRoutineId;

    @Builder.Default
    @JsonBackReference
    @OneToMany(mappedBy = "routine")
    private List<UserRoutine> userRoutines = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "routine")
    private List<RoutineExercise> routineExercises = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "routine")
    private List<SessionLog> routineSessionLogs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routine routine = (Routine) o;
        return id.equals(routine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
