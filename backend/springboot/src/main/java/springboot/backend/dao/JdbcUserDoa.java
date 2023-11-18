package springboot.backend.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import springboot.backend.exception.UserNotFoundException;
import springboot.backend.model.User;

import java.util.Objects;

@Component
public class JdbcUserDoa implements UserDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcUserDoa(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()) {
            return mapRowToUser(results);
        } else {
            throw new UserNotFoundException();
        }
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
