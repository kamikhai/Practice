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
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @Lob
    @Column(nullable = false)
    private String description;

    @ManyToMany
    private List<Tag> tags;
}
