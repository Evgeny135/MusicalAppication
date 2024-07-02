package org.application.musicalappication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Включение защиты
@EnableMethodSecurity // Включениедоступа к методам по роллям
public class SecurityConfig {
    // Настройка фильтра запросов
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()  // Делаем все запросы с корнем public с общим доступом
                .requestMatchers("/secured/**").authenticated() // Делаем все запросы с корнем secured с закрытым доступом
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll); // Делаем форму входа общедоступной
        return http.build();
    }
    // Управление пользователями
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Инициализация юзерСервиса
        UserService users = new UserService();
        // Добавляем пользователей в юзерСервис
        users.addUser(new org.application.musicalappication.security.User(0,"user@sber.ru",passwordEncoder().encode("user"),"ROLE_USER"));
        users.addUser(new org.application.musicalappication.security.User(1,"admin@sber.ru",passwordEncoder().encode("admin"),"ROLE_ADMIN"));
        users.addUser(new org.application.musicalappication.security.User(2,"owner@sber.ru",passwordEncoder().encode("owner"),"ROLE_ADMIN ROLE_USER"));
        return users;
    }

    // Инициализация шифратора паролей
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
