package springDemo.simpleproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springDemo.simpleproject.repository.*;
import springDemo.simpleproject.service.UserService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager eManager;
    public SpringConfig(DataSource dataSource, EntityManager eManager) {
        this.dataSource = dataSource;
        this.eManager = eManager;
    }

    @Bean
    public UserRepositoryInterface userRepository(){
//        return new UserRepository();
//        return new UserRepositoryJDBC(dataSource);
//        return new UserRepositoryJDBCtemplate(dataSource);
        return new UserRepositoryJPA(eManager);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
