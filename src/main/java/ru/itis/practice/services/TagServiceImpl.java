package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.Tag;
import ru.itis.practice.repositories.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAllByOrderByName();
    }

<<<<<<< HEAD
	@Override
	public Tag getByName(String name) {
    	return tagRepository.findByName(name);
	}
=======
    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }
>>>>>>> 7b3384afdab8d0ac362e93800cb4b9e7bcabd271
}
