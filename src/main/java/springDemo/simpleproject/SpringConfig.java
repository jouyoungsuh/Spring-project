package springDemo.simpleproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springDemo.simpleproject.repository.UserRepository;
import springDemo.simpleproject.service.UserService;

@Configuration
public class SpringConfig {

    @Bean
    public UserRepository userRepository(){
        return new UserRepository();
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
