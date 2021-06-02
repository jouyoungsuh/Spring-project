package springDemo.simpleproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springDemo.simpleproject.aop.TimeTraceAOP;
import springDemo.simpleproject.repository.*;
import springDemo.simpleproject.service.UserService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final UserRepositoryInterface userRepository;
    public SpringConfig(UserRepositoryInterface memberRepository) {
        this.userRepository = memberRepository;
    }
    @Bean
    public UserService memberService() {
        return new UserService(userRepository);
    }

    // Add below if not going to use @Component on AOP
//    @Bean
//    public TimeTraceAOP tta(){
//        return new TimeTraceAOP();
//    }
}


//    private final DataSource dataSource;
//    private final EntityManager eManager;
//    public SpringConfig(DataSource dataSource, EntityManager eManager) {
//        this.dataSource = dataSource;
//        this.eManager = eManager;
//    }
//
//    @Bean
//    public UserRepositoryInterface userRepository(){
////        return new UserRepository();
////        return new UserRepositoryJDBC(dataSource);
////        return new UserRepositoryJDBCtemplate(dataSource);
//        return new UserRepositoryJPA(eManager);
//    }
//
//    @Bean
//    public UserService userService() {
//        return new UserService(userRepository());
//    }

