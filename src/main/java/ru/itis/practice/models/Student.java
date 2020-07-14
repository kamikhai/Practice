package ru.itis.practice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    private User user;

    @ManyToOne(optional = false)
    private Group group;

    @ManyToOne
    private JobProfile jobProfile;

    @OneToMany
    private List<Competence> competences;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String description;
}
