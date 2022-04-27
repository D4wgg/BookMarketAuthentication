package ru.dawgg.bookmarket.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.dawgg.bookmarket.exception.ApiEntityNotFoundException;
import ru.dawgg.bookmarket.model.Token;
import ru.dawgg.bookmarket.repository.TokenRepository;
import ru.dawgg.bookmarket.security.token.TokenAuthentication;

import java.util.Optional;

import static ru.dawgg.bookmarket.exception.ApiEntityNotFoundException.TOKEN_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> tokenCandidate = tokenRepository.findOneByValue(tokenAuthentication.getName());

        if (tokenCandidate.isPresent()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else throw new ApiEntityNotFoundException(TOKEN_NOT_FOUND_EXCEPTION);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
