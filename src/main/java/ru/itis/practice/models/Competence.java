package ru.itis.practice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competence {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne
    private Teacher confirmedBy;

    @ManyToMany
    private List<Tag> tags;

    @Column(nullable = false)
    private String description;
}
