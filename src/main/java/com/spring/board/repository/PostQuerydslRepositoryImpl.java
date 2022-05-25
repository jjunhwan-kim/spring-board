package com.spring.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.board.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.board.domain.QPost.post;

public class PostQuerydslRepositoryImpl implements PostQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostQuerydslRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Post> findAllByIdDesc() {
        return jpaQueryFactory.selectFrom(post)
                .orderBy(post.id.desc())
                .fetch();
    }
}
