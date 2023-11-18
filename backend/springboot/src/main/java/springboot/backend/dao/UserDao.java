package springboot.backend.dao;

import springboot.backend.model.User;

public interface UserDao {
    User findUserByUsername(String username);
}
