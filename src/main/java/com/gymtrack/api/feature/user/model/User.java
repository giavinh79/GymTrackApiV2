package com.gymtrack.api.feature.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(
        name = "email_uidx",
        columnNames = "email"
))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String lastLoggedInIp;

    @Column
    private LocalDateTime lastLoggedInDate;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<UserRoutine> userRoutines = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SessionLog> routineSessionLogs = new ArrayList<>();

    @Transient
    public int getAge() {
        // TODO - store birth dates and compute age via this logic
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
