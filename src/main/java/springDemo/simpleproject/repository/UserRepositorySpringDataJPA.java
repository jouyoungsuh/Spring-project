package springDemo.simpleproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springDemo.simpleproject.domain.User;

import java.util.Optional;

public interface UserRepositorySpringDataJPA extends JpaRepository<User, Integer>, UserRepositoryInterface {

    @Override
    Optional<User> findByName(String name);

}
