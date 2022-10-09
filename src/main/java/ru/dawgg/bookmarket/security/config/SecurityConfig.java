package ru.dawgg.bookmarket.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.dawgg.bookmarket.security.filter.TokenAuthFilter;
import ru.dawgg.bookmarket.security.provider.TokenAuthenticationProvider;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationProvider provider;
    private final TokenAuthFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(filter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(provider)
                .authorizeRequests()
                    .antMatchers("/api/v1/login").permitAll()
                    .antMatchers("/api/v1/signUp").permitAll()
                    .antMatchers("/swagger-ui/**", "/javainuse-openapi/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}