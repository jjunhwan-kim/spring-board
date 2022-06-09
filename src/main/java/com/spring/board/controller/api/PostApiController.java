package com.spring.board.controller.api;

import com.spring.board.controller.api.dto.PostCreateDto;
import com.spring.board.controller.api.dto.PostListResponseDto;
import com.spring.board.controller.api.dto.PostResponseDto;
import com.spring.board.controller.api.dto.PostUpdateDto;
import com.spring.board.domain.Post;
import com.spring.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public PostResponseDto create(@RequestBody PostCreateDto postCreateDto) {
        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        Post savedPost = postService.save(post);

        return PostResponseDto.of(savedPost);
    }

    @GetMapping("/api/posts/{id}")
    public PostResponseDto read(@PathVariable Long id) {
        Post post = postService.findOne(id);
        return PostResponseDto.of(post);
    }

    @PutMapping("/api/posts/{id}")
    public PostResponseDto update(@RequestBody PostUpdateDto postUpdateDto) {
        Post post = new Post();
        post.setId(postUpdateDto.getId());
        post.setTitle(postUpdateDto.getTitle());
        post.setContent(postUpdateDto.getContent());
        Post updatedPost = postService.update(post);

        return PostResponseDto.of(updatedPost);
    }

    @DeleteMapping("/api/posts/{id}")
    public PostResponseDto delete(@PathVariable Long id) {
        Post deletedPost = postService.delete(id);
        return PostResponseDto.of(deletedPost);
    }

    @GetMapping("/api/posts")
    public PostListResponseDto list() {
        List<Post> posts = postService.findAll();
        PostListResponseDto postListResponseDto = new PostListResponseDto();
        posts.forEach(post ->
                postListResponseDto.getPosts().add(PostResponseDto.of(post)));
        return postListResponseDto;
    }
}
