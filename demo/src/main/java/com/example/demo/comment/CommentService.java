package com.example.demo.comment;

import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    public List<Comment> getAllComments(){return commentRepository.findAll();}

    public void insertComment(Comment comment){
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("makeComment");
        query.setParameter("postID", comment.getPost_id());
        query.setParameter("personID", comment.getPerson_id());
        query.setParameter("new_description", comment.getDescription());
        query.execute();
    }
}
