package com.example.questapp.controllers;

import com.example.questapp.entities.Like;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.response.LikeResponse;
import com.example.questapp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return likeService.getAllLike(postId, userId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest newLikeRequest){
         return likeService.createOneLike(newLikeRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
