package com.example.questapp.service;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repository.CommentRepository;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;
import com.example.questapp.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponse> getAllComment(Optional<Long> postId, Optional<Long> userId) {
        List<Comment> comments;
        if (postId.isPresent() && userId.isPresent())
            comments = commentRepository.findByPostIdAndUserId(postId.get(), userId.get());

        else if (postId.isPresent())
            comments = commentRepository.findByPostId(postId.get());

        else if (userId.isPresent())
            comments = commentRepository.findByUserId(userId.get());
        else
            comments = commentRepository.findAll();

        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userService.getOneUserById(newCommentRequest.getUserId());
        Post post = postService.getOnePostById(newCommentRequest.getPostId());

        if (user == null || post == null) {
            return null;
        }

        Comment toSave = new Comment();
        toSave.setId(newCommentRequest.getId());
        toSave.setText(newCommentRequest.getText());
        toSave.setPost(post);
        toSave.setUser(user);
        toSave.setCreateDate(new Date());

        return commentRepository.save(toSave);
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()){
            Comment toSave = comment.get();
            toSave.setText(updateComment.getText());
            commentRepository.save(toSave);
            return toSave;
        }

        return null;
    }

    public void deleteOneCommentById(Long id){
        commentRepository.deleteById(id);
    }
}
