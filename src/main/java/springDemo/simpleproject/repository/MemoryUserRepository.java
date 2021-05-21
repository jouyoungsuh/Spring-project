package springDemo.simpleproject.repository;

import springDemo.simpleproject.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryUserRepository implements UserRepository{
    private static Map<Integer, User> dataMap = new ConcurrentHashMap<>();
    private static AtomicInteger sequence = new AtomicInteger();

    @Override
    public User save(User user) {
        user.setId(sequence.addAndGet(1));
        dataMap.put(user.getId(), user);
        return user;
    }
    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(dataMap.get(id));
    }
    @Override
    public List<User> findAll() {
        return new ArrayList<>(dataMap.values());
    }
    @Override
    public Optional<User> findByName(String name) {
        return dataMap.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        dataMap.clear();
    }

}
