package ru.dawgg.bookmarket.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.dawgg.bookmarket.exception.TokenNotFoundException;
import ru.dawgg.bookmarket.repository.TokenRepository;
import ru.dawgg.bookmarket.security.token.TokenAuthentication;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var tokenAuthentication = (TokenAuthentication) authentication;
        var token = tokenRepository.findOneByValue(tokenAuthentication.getName())
                .orElseThrow(TokenNotFoundException::new);
        var userDetails = userDetailsService.loadUserByUsername(token.getUser().getEmail());
        tokenAuthentication.setUserDetails(userDetails);
        tokenAuthentication.setAuthenticated(true);
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
