package com.spring.board.controller.api.dto;

import com.spring.board.domain.Post;
import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;

    public static PostResponseDto of(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setContent(post.getContent());
        return postResponseDto;
    }
}
