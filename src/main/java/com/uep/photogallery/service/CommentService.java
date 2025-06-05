package com.uep.photogallery.service;

import com.uep.photogallery.model.Comment;
import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.CommentRepository;
import com.uep.photogallery.repository.PhotoRepository;
import com.uep.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, 
                         PhotoRepository photoRepository,
                         UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment createComment(Long photoId, String content) {
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));
        
        // For now, we'll use the first user as the comment author
        // In a real application, this would be the authenticated user
        User user = userRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No users found"));

        Comment comment = new Comment(content, photo, user);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPhoto(Long photoId) {
        return commentRepository.findByPhotoId(photoId);
    }

    @Transactional
    public Comment updateComment(Long id, String content) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(content);
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
} 