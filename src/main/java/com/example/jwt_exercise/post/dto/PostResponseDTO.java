package com.example.jwt_exercise.post.dto;

import com.example.jwt_exercise.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private Long postId;
    private Long authorId;
    private String authorName;
    private String title;
    private String content;

    public static PostResponseDTO from(Post post){
        return PostResponseDTO.builder()
                .postId(post.getId())
                .authorId(post.getAuthor().getId())
                .authorName(post.getAuthor().getName())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
