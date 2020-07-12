package ru.itis.practice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Lob
    @Column(nullable = false)
    private String description;
}
