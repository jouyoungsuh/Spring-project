package springDemo.simpleproject.repository;

import springDemo.simpleproject.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJPA implements UserRepositoryInterface {
    private final EntityManager eManager;
    public UserRepositoryJPA(EntityManager em) {
        this.eManager = em;
    }
    public User save(User user) {
        eManager.persist(user);
        return user;
    }
    public Optional<User> findById(Integer id) {
        User user = eManager.find(User.class, id);
        return Optional.ofNullable(user);
    }
    public Optional<User> findByName(String name) {
        List<User> res = eManager.createQuery("select m from User m where m.name = :name", User.class)
                                    .setParameter("name", name)
                                    .getResultList();
        return res.stream().findAny();
    }
    public List<User> findAll() {
        List<User> res = eManager.createQuery("select m from User m", User.class)
                                 .getResultList();
        return res;
    }
}
