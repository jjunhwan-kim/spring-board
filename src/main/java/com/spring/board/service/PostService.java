package com.spring.board.service;

import com.spring.board.domain.Post;
import com.spring.board.repository.PostDataJpaRepository;
import com.spring.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    //private final PostDataJpaRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findOne(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalStateException("Entity Not Found"));
    }

    public Post update(Post post) {
        //postRepository.update(post); // Jdbc, JdbcTemplate

        // Jpa
        Post foundPost = postRepository.findById(post.getId()).orElseThrow(() -> new IllegalStateException("Entity Not Found"));
        foundPost.setTitle(post.getTitle());
        foundPost.setContent(post.getContent());
        return foundPost;
    }

    public Post delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("Entity Not Found"));
        postRepository.deleteById(id);
        return post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
        //return postRepository.findAllByOrderByIdDesc();
        //return postRepository.findAllByIdDesc();
    }
}