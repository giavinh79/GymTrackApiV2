package com.gymtrack.api.feature.user.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firebaseId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String lastLoggedInIp;

    @Column
    private Timestamp lastLoggedInDate;

    @Column
    @CreatedDate
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private Timestamp deletedAt;

    @Transient
    public int getAge() {
        // @TODO - store birth dates and compute age via this logic
        return 0;
    };
}
