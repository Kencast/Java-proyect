package com.example.demo.tag;

import com.example.demo.person.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/get")
    public List<Tag> getTags() {
        return tagService.getAllTag();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        try{
            tagService.createTag(tag);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the tag");
        }
    }

}
