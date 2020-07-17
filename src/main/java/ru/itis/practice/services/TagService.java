package ru.itis.practice.services;

import ru.itis.practice.models.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getAll();
    Tag getByName(String name);
}
