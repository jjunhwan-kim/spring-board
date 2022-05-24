package com.spring.board.repository;

import com.spring.board.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    Post update(Post post);
    void deleteById(Long id);
    List<Post> findAll();
}