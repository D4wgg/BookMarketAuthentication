package ru.dawgg.bookmarket.security.filter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.dawgg.bookmarket.security.token.TokenAuthentication;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        String token = req.getParameter("token");
        var tokenAuthentication = new TokenAuthentication(token);

        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        chain.doFilter(req, resp);
    }
}
