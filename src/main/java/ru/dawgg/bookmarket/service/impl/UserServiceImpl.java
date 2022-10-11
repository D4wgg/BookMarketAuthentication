package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.exception.UserAlreadyExistException;
import ru.dawgg.bookmarket.exception.UserNotFoundException;
import ru.dawgg.bookmarket.model.User;
import ru.dawgg.bookmarket.model.characteristic.Role;
import ru.dawgg.bookmarket.model.characteristic.State;
import ru.dawgg.bookmarket.repository.UserRepository;
import ru.dawgg.bookmarket.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User buildUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .hashPassword(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .role(Role.USER)
                .state(State.ACTIVE)
                .locked(false)
                .enabled(false)
                .build();
    }

    @Override
    public boolean userAlreadyExists(User user) {
        if (userRepository.findOneByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(user.getEmail());
        }
        return false;
    }
}