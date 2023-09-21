package com.project.food.configuration;

import com.project.food.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private User user;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/api/v1/dishes").permitAll()
                                .requestMatchers("/swagger-ui").permitAll()
                                .requestMatchers("/api-docs/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .requestMatchers("/dashboard").hasAnyRole("ADMIN", "OWNER", "USER")
                                .requestMatchers("/restaurant-dashboard").hasAnyRole("ADMIN", "OWNER", "USER")
                                .requestMatchers("/restaurant-owner").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/restaurant-users").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/register-restaurant").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/register-restaurant/save").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/add-new-dish").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/add-new-dish/save").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/order/save").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/edit-status/**").hasAnyRole("ADMIN", "OWNER")
                                .requestMatchers("/edit-status/save").hasAnyRole("ADMIN", "OWNER")

                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/dashboard")
                                .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
