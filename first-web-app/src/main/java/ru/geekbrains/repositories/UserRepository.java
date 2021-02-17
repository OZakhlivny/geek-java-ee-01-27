package ru.geekbrains.repositories;

import ru.geekbrains.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public void saveOrUpdate(User user) {
        if (user.getId() == null) user.setId(identity.incrementAndGet());
        userMap.put(user.getId(), user);
    }

    public void deleteById(Long id) {
        userMap.remove(id);
    }
}
