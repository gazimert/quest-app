package com.example.questapp.response;

import com.example.questapp.entities.User;

public class UserResponse {

    Long id;
    int avatarId;
    String userName;

    public UserResponse(User entity){
        this.id = entity.getId();
        this.avatarId = entity.getAvatar();
        this.userName = entity.getUserName();
    }

    public UserResponse(){

    }

    public UserResponse(Long id, int avatarId, String userName) {
        this.id = id;
        this.avatarId = avatarId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", avatarId=" + avatarId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
