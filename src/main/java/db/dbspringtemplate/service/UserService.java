package db.dbspringtemplate.service;

import db.dbspringtemplate.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    List<UserDto> getAllUsersByName(String name);

    UserDto getUser(Long id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    void inactiveUser(Long id);
}
