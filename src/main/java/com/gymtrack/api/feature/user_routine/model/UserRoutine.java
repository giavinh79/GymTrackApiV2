package com.gymtrack.api.feature.user_routine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_user_routine")
public class UserRoutine {
    @EmbeddedId
    private UserRoutineId id;

    @JsonManagedReference
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "app_user_id")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @MapsId("routineId")
    @JoinColumn(name = "routine_id")
    private Routine routine;

    private Boolean isSelected;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoutine that = (UserRoutine) o;
        return id.equals(that.id);
    }
}
