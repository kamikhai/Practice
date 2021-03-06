package ru.itis.practice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @ManyToOne
    private JobProfile jobProfile;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "varchar(255) default 'Нет опыта'")
    private String workExperience;

    @Column(columnDefinition = "varchar(255) default 'https://vk.com/id0'")
    private String link;
}
