package com.gymtrack.api.feature.user_routine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoutineId implements Serializable {
    @Column(name = "app_user_id")
    private Integer userId;

    @Column(name = "routine_id")
    private Integer routineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoutineId that = (UserRoutineId) o;
        return userId.equals(that.userId) && routineId.equals(that.routineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, routineId);
    }
}