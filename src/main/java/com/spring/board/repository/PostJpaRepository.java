package com.spring.board.repository;

import com.spring.board.domain.Post;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PostJpaRepository implements PostRepository {

    private final EntityManager entityManager;

    @Override
    public Post save(Post post) {
        entityManager.persist(post);
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = entityManager.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public Post update(Post post) {
        Post foundPost = entityManager.find(Post.class, post.getId());
        foundPost.setTitle(post.getTitle());
        foundPost.setContent(post.getContent());
        return post;
    }

    @Override
    public void deleteById(Long id) {
        Post foundPost = entityManager.find(Post.class, id);
        entityManager.remove(foundPost);
    }

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }
}
