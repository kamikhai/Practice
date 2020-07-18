package ru.itis.practice.services;

import ru.itis.practice.models.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> getAll();

    Tag getByName(String name);

    Tag save(Tag tag);

    Optional<Tag> findById(Long id);

    void delete(Tag tag);
}
