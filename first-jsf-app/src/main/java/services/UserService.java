package services;

import dto.UserDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    void saveOrUpdate(UserDto user);

    void deleteById(Long id);

    Long countAll();
}
