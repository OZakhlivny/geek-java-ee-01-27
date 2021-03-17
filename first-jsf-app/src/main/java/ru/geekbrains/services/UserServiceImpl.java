package ru.geekbrains.services;

import ru.geekbrains.dto.UserDto;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.UserRepository;
import ru.geekbrains.rest.UserServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService, UserServiceRest {

    @EJB
    private UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        if(user != null) return new UserDto(userRepository.findById(id));
        return null;
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(UserDto user) {
        userRepository.saveOrUpdate(new User(user));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long countAll() {
        return userRepository.countAll();
    }

    @Override
    public void insert(UserDto user) {
        if(user.getId() != null) throw new IllegalArgumentException();
        saveOrUpdate(user);
    }

    @Override
    public void update(UserDto user) {
        if(user.getId() == null) throw new IllegalArgumentException();
        saveOrUpdate(user);
    }
}
