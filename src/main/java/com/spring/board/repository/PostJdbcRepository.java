package com.spring.board.repository;

import com.spring.board.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PostJdbcRepository implements PostRepository {

    private final DataSource dataSource;

    @Override
    public Post save(Post post) {
        String sql = "insert into post (title, content) values (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                post.setId(resultSet.getLong(1));
            } else {
                throw new IllegalStateException("id 조회 실패");
            }
            return post;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        String sql = "select * from post where post_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("post_id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                return Optional.of(post);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Post update(Post post) {
        String sql = "update post set title = ?, content = ? where post_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setLong(3, post.getId());
            preparedStatement.executeUpdate();
            return post;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from post where post_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<Post> findAll() {
        String sql = "select * from post";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();

            while (rs.next()) {
                Post member = new Post();
                member.setId(rs.getLong("post_id"));
                member.setTitle(rs.getString("title"));
                member.setContent(rs.getString("content"));
                posts.add(member);
            }
            return posts;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, rs);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    private Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
    }
}
