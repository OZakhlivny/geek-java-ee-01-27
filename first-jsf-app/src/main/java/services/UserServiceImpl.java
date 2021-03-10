package services;

import dto.UserDto;
import entities.User;
import repositories.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService{

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
}
