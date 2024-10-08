package com.example.questapp.service;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repository.PostRepository;
import com.example.questapp.requests.PostCreateRequest;
import com.example.questapp.requests.PostUpdateRequest;
import com.example.questapp.response.LikeResponse;
import com.example.questapp.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    @Lazy
    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent())
            list = postRepository.findByUserId(userId.get());
        else
            list = postRepository.findAll();

        return list.stream().map(p ->{
                List<LikeResponse> likes = likeService.getAllLike(Optional.of(p.getId()), Optional.empty());
                return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public PostResponse getOnePostByIdWithLikes(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<LikeResponse> likes = likeService.getAllLike(Optional.of(postId), Optional.empty());
        return new PostResponse(post,likes);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null)
            return null;

        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setText(newPostRequest.getText());
        toSave.setUser(user);
        toSave.setCreateDate(new Date());
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setTitle(updatePost.getTitle());
            toUpdate.setText(updatePost.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        }

        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
