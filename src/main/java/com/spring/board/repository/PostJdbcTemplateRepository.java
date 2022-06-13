package com.spring.board.repository;

import com.spring.board.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PostJdbcTemplateRepository implements PostRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public PostJdbcTemplateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(Post post) {
        log.info("PostJdbcTemplateRepository.save");
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("post_id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        log.info("PostJdbcTemplateRepository.findById");
        List<Post> posts = jdbcTemplate.query("select * from post where post_id = ?", postRowMapper(), id);
        return posts.stream().findAny();
    }

    @Override
    public Post update(Post post) {
        log.info("PostJdbcTemplateRepository.update");
        jdbcTemplate.update("update post set title = ?, content = ? where post_id = ?", post.getTitle(), post.getContent(), post.getId());
        return post;
    }

    @Override
    public void deleteById(Long id) {
        log.info("PostJdbcTemplateRepository.deleteById");
        jdbcTemplate.update("delete from post where post_id = ?", id);
    }

    @Override
    public List<Post> findAll() {
        log.info("PostJdbcTemplateRepository.findAll");
        List<Post> posts = jdbcTemplate.query("select * from post", postRowMapper());
        return posts;
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("post_id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            return post;
        };
    }
}
