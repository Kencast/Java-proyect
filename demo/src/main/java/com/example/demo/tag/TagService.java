package com.example.demo.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }
}
