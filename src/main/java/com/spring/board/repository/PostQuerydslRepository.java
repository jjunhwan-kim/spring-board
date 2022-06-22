package com.spring.board.repository;

import com.spring.board.domain.Post;

import java.util.List;

public interface PostQuerydslRepository {
    List<Post> findAllByIdDesc();
    Boolean existsAllByIdIn(List<Long> ids);
}
