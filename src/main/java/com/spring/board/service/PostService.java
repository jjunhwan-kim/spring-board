package com.spring.board.service;

import com.spring.board.domain.Post;
import com.spring.board.repository.PostDataJpaRepository;
import com.spring.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    //private final PostRepository postRepository;
    private final PostDataJpaRepository postRepository;

    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    public Long update(Post post) {
        //postRepository.update(post); // Jdbc, JdbcTemplate, Jpa
        postRepository.save(post); // Data Jpa
        return post.getId();
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        //return postRepository.findAll();
        //return postRepository.findAllByOrderByIdDesc();
        return postRepository.findAllByIdDesc();
    }
}