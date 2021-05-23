package springDemo.simpleproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springDemo.simpleproject.domain.User;
import springDemo.simpleproject.repository.MemoryUserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    MemoryUserRepository userRepository;
    UserService userService;

    @BeforeEach
    public void beforeEach() {
        this.userRepository = new MemoryUserRepository();
        this.userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach() {
        this.userRepository.clearStored();
    }

    @Test
    void signUp() {
        // Given
        User user = new User();
        user.setName("example-name");

        // When
        Integer saveId = userService.signUp(user);

        // Then
        User findMember = userRepository.findById(saveId).get();
        Assertions.assertEquals(user.getName(), findMember.getName());
    }

    @Test
    void findUsers() {
        // Given
        User user1 = new User();
        user1.setName("Identical Name");

        User user2 = new User();
        user2.setName("Identical Name");

        // When
        userService.signUp(user1);

        // Then 1
        try {
            userService.signUp(user2);
        }
        catch(IllegalStateException error2) {
            Assertions.assertEquals(error2.getMessage(), "User with same name already exists");
        }

        // Then 2 : same as above
        IllegalStateException error2 = assertThrows(IllegalStateException.class,
        () -> userService.signUp(user2));

        Assertions.assertEquals(error2.getMessage(), "User with same name already exists");
    }

}