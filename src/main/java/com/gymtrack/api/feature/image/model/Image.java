package com.gymtrack.api.feature.image.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymtrack.api.feature.exercise.model.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String url;

    private String fileId;

    private String extension;

    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "image")
    private List<Exercise> exercisesApplicable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
