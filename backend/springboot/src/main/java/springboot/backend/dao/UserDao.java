package springboot.backend.dao;

import springboot.backend.model.RegisterUser;
import springboot.backend.model.User;

public interface UserDao {
    User findUserByUsername(String username);
    boolean registerUser(RegisterUser user);
    boolean create(String username, String email, String password, String role);
}
