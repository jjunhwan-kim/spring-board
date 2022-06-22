package com.spring.board.repository;

import com.spring.board.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostQuerydslRepositoryImplTest {

    @Autowired
    private PostDataJpaRepository repository;

    @Test
    void existsAllbyIdIn() {
        List<Post> posts = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Post post = new Post();
            post.setTitle("test title " + i);
            post.setContent("test content " + i);
            repository.save(post);
            posts.add(post);
            ids.add(post.getId());
        }

        assertThat(repository.existsAllByIdIn(ids)).isTrue();
        ids.add(posts.get(posts.size() - 1).getId() + 1);

        assertThat(repository.existsAllByIdIn(ids)).isFalse();
    }
}