package com.example.questapp.service;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repository.LikeRepository;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService,
                       PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLike(Optional<Long> postId, Optional<Long> userId) {
        List<Like> list;
        if (postId.isPresent() && userId.isPresent())
            list = likeRepository.findByPostIdAndUserId(postId.get(), userId.get());

        else if (postId.isPresent())
            list = likeRepository.findByPostId(postId.get());

        else if (userId.isPresent())
            list = likeRepository.findByUserId(userId.get());
        else
            list = likeRepository.findAll();

        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like createOneLike(LikeCreateRequest newLikeRequest) {
        User user = userService.getOneUserById(newLikeRequest.getUserId());
        Post post = postService.getOnePostById(newLikeRequest.getPostId());

        if (user == null || post == null)
            return null;

        Like toSave = new Like();
        toSave.setId(newLikeRequest.getId());
        toSave.setUser(user);
        toSave.setPost(post);

        return likeRepository.save(toSave);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
