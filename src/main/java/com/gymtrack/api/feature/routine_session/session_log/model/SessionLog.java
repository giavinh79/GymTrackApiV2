package com.gymtrack.api.feature.routine_session.session_log.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "session_log")
public class SessionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionLog that = (SessionLog) o;
        return id.equals(that.id);
    }
}
