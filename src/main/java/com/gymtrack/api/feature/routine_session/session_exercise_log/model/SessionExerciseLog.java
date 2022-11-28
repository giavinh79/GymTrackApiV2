package com.gymtrack.api.feature.routine_session.session_exercise_log.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.set.model.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "session_exercise_log")
public class SessionExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session_log_id")
    private SessionLog sessionLog;

    @ManyToMany
    @JoinTable(
            name = "session_exercise_set_log",
            joinColumns = @JoinColumn(name = "session_exercise_log_id"),
            inverseJoinColumns = @JoinColumn(name = "set_id")
    )
    private List<Set> sets = new ArrayList<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionExerciseLog that = (SessionExerciseLog) o;
        return id.equals(that.id);
    }
}
