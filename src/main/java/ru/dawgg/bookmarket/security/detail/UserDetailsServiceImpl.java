package ru.dawgg.bookmarket.security.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dawgg.bookmarket.exception.ApiEntityNotFoundException;
import ru.dawgg.bookmarket.repository.UserRepository;

import static ru.dawgg.bookmarket.exception.ApiEntityNotFoundException.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(repository.findOneByLogin(username)
                .orElseThrow(new ApiEntityNotFoundException(USER_NOT_FOUND_EXCEPTION)));
    }
}
