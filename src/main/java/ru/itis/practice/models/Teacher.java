package ru.itis.practice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Group> curatedGroups;

    private String position;

    private String information;

    @Column(columnDefinition = "varchar(255) default 'https://vk.com/id0'")
    private String link;
}