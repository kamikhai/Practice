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
public class Teacher {
    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    private User user;

    @ManyToMany
    private List<Group> curatedGroups;

    private String position;

    private String information;
}
