package db.dbspringtemplate.service.impl;

import db.dbspringtemplate.dto.UserDto;
import db.dbspringtemplate.error.RestException;
import db.dbspringtemplate.model.User;
import db.dbspringtemplate.repository.UserRepository;
import db.dbspringtemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return UserDto.fromEntity(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersByName(String name) {
        return UserDto.fromEntity(userRepository.findAllUserByName(name));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
        });
        return UserDto.fromEntity(user);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, "E-mail já cadastrado");
        });

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .birthdate(userDto.getBirthdate())
                .password(userDto.getPassword())
                .build();

        return UserDto.fromEntity(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
        });

        if (!user.getEmail().equals(userDto.getEmail())) {
            userRepository.findByEmail(userDto.getEmail()).ifPresent(x -> {
                throw new RestException(HttpStatus.NOT_FOUND, "E-mail: "+x.getEmail()+" já cadastrado");
            });
        }

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setBirthdate(userDto.getBirthdate());

        return UserDto.fromEntity(user);
    }

    @Override
    @Transactional
    public void inactiveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
        });

        this.userRepository.delete(user);
    }
}
