package springDemo.simpleproject.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import springDemo.simpleproject.domain.User;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import java.util.List;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository;

    @BeforeEach
    public void beforeEach(){
        this.repository = new MemoryUserRepository();
    }

    @AfterEach
    public void afterEach(){
        this.repository.clearStored();
    }

    @Test
    public void save(){
        User user = new User();
        user.setName("name Example 1");
        repository.save(user);

        User result = repository.findById(user.getId()).get();
        //Assertions.assertEquals(user, result); // Either works
        Assertions.assertThat(result).isEqualTo(user);

    }

    @Test
    public void findByName(){
        User user1 = new User();
        user1.setName("user_1");
        repository.save(user1);

        User user2 = new User();
        user2.setName("user_2");
        repository.save(user2);

        User result = repository.findByName("user_1").get();
        Assertions.assertThat(result).isEqualTo(user1);

        /* This will return false
        User result2 = repository.findByName("user_1").get();
        Assertions.assertThat(result).isEqualTo(user1);
        */
    }

    @Test
    public void findAll(){
        User user1 = new User();
        user1.setName("user_1");
        repository.save(user1);

        User user2 = new User();
        user2.setName("user_2");
        repository.save(user2);

        User user3 = new User();
        user3.setName("user_3");
        repository.save(user3);

        List<User> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(3);

    }

}

