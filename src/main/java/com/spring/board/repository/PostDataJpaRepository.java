package com.spring.board.repository;

import com.spring.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDataJpaRepository extends JpaRepository<Post, Long> {
}
