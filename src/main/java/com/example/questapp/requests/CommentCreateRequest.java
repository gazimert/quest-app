package com.example.questapp.requests;

public class CommentCreateRequest {

    Long id;
    String text;
    Long postId;
    Long userId;

    public CommentCreateRequest() {

    }

    public CommentCreateRequest(Long id, String text, Long postId, Long userId) {
        this.id = id;
        this.text = text;
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CommentCreateRequest{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
