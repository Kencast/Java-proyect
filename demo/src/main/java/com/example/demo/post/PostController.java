package com.example.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get")
    public List<Post> getAllPost() {
        return postService.findAllPost();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostTag postTag) {
        try{
            Post post = new Post(postTag.getId(),postTag.getTitle(),postTag.getDescription(),
                    postTag.getPublicationDate(),postTag.getPerson_id());
            postService.insertPost(post,postTag.getTags());
            return ResponseEntity.ok("ok");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the post."+e.getMessage());
        }
    }
}
