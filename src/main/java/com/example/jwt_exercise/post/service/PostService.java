package com.example.jwt_exercise.post.service;

import com.example.jwt_exercise.post.domain.Post;
import com.example.jwt_exercise.post.dto.PostRequestDTO;
import com.example.jwt_exercise.post.dto.PostResponseDTO;
import com.example.jwt_exercise.post.repository.PostRepository;
import com.example.jwt_exercise.user.domain.User;
import com.example.jwt_exercise.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDTO createPost(PostRequestDTO requestDTO,String name){
        User user=userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        Post post=Post.builder()
                .author(user)
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .build();
        postRepository.save(post);
        return PostResponseDTO.from(post);
    }

    public List<PostResponseDTO> getAllPosts(){
        List<Post>posts=postRepository.findAll()
                .stream()
                .collect(Collectors.toList());

        return posts.stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
    }
}
