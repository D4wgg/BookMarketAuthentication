package ru.dawgg.bookmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dawgg.bookmarket.dto.UserDto;
import ru.dawgg.bookmarket.exception.InnerApiException;
import ru.dawgg.bookmarket.model.User;
import ru.dawgg.bookmarket.model.characteristic.Role;
import ru.dawgg.bookmarket.model.characteristic.State;
import ru.dawgg.bookmarket.repository.UserRepository;
import ru.dawgg.bookmarket.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.dawgg.bookmarket.exception.ApiEntityNotFoundException.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public void signUp(UserDto userDto) {
        userRepository.save(User.builder()
                .login(userDto.getLogin())
                .hashPassword(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build());
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findOneById(Long id) {
        Optional<User> userCandidate = userRepository.findById(id);
        User user = null;

        if (userCandidate.isPresent()) {
            user = userCandidate.get();
        } else throw new InnerApiException(USER_NOT_FOUND_EXCEPTION);

        return mapper.map(user, UserDto.class);
    }
}