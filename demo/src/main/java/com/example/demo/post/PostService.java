package com.example.demo.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EntityManager entityManager;

    public List<Post> findAllPost() {return postRepository.findAll();}

    public void insertPost(Post post, String tags) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("createPost");
        query.setParameter("title_c", post.getTitle());
        query.setParameter("description", post.getDescription());
        query.setParameter("date_c", post.getPublicationDate());
        query.setParameter("personId", post.getPerson());
        query.setParameter("tags", tags);
        query.execute();
    }
}
