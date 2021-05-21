package springDemo.simpleproject.repository;

import springDemo.simpleproject.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByName(String name);
    List<User> findAll();

}
