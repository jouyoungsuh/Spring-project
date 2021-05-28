package springDemo.simpleproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springDemo.simpleproject.repository.UserRepository;
import springDemo.simpleproject.repository.UserRepositoryInterface;
import springDemo.simpleproject.repository.UserRepositoryJDBC;
import springDemo.simpleproject.service.UserService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserRepositoryInterface userRepository(){
        //return new UserRepository();
        return new UserRepositoryJDBC(dataSource);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
