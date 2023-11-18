package springboot.backend.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import springboot.backend.exception.UserAlreadyExistsException;
import springboot.backend.model.RegisterUser;
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
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }
    }

    @Override
    public boolean registerUser(RegisterUser user) {
        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());

        try {
            User existingUser = findUserByUsername(user.getUsername());
            throw new UserAlreadyExistsException();
        } catch (UsernameNotFoundException e) {
            return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), password_hash) == 1;
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
