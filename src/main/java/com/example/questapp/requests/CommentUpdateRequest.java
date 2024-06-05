package com.example.questapp.requests;

public class CommentUpdateRequest {

    private String text;

    public CommentUpdateRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentUpdateRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}
