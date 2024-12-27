package com.example.jwt_exercise.post.controller;

import com.example.jwt_exercise.post.domain.Post;
import com.example.jwt_exercise.post.dto.PostRequestDTO;
import com.example.jwt_exercise.post.dto.PostResponseDTO;
import com.example.jwt_exercise.post.service.PostService;
import com.example.jwt_exercise.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping//AuthenticationPrincipal 을 사용하렴녀 TokenProvider 를 수정해야할 상황이 생길 가능성이 높으므로 조심해야한다.
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO, @AuthenticationPrincipal String name){
        PostResponseDTO responseDTO=postService.createPost(requestDTO,name);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        List<PostResponseDTO> posts=postService.getAllPosts();
        return ResponseEntity.ok()
                .body(posts);
    }
}
