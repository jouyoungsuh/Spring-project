package springDemo.simpleproject.service;

import springDemo.simpleproject.domain.User;
import springDemo.simpleproject.repository.MemoryUserRepository;
import springDemo.simpleproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/*
    This class manages the business logic and the services that the whole project is serving to the user.
 */
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Integer signUp(User user){
        checkDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void checkDuplicateUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent() == true){
            throw new IllegalStateException("User with same name already exists");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserByID(User user){
        return userRepository.findById(user.getId());
    }
}
