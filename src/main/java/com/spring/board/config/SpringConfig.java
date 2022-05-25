package com.spring.board.config;

import com.spring.board.repository.PostJdbcRepository;
import com.spring.board.repository.PostJdbcTemplateRepository;
import com.spring.board.repository.PostJpaRepository;
import com.spring.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager entityManager;

    @Bean
    public PostRepository postRepository() {
        //return new PostJdbcRepository(dataSource);
        //return new PostJdbcTemplateRepository(dataSource);
        return new PostJpaRepository(entityManager);
    }
}
