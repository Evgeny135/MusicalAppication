package org.application.musicalappication.config;

import org.application.musicalappication.security.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity // Включение защиты
@EnableMethodSecurity // Включениедоступа к методам по роллям
public class SecurityConfig {
    // Настройка фильтра запросов

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/public/**").permitAll()  // Делаем все запросы с корнем public с общим доступом
                .requestMatchers("/secured/**").authenticated() // Делаем все запросы с корнем secured с закрытым доступом
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(logout -> logout.logoutSuccessUrl("/public/welcome")); // Делаем форму входа общедоступной
        return http.build();
    }

    @Autowired
    ClientService clientService;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clientService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Primary
    @Bean
    public AuthenticationManagerBuilder  configureAuthenticationManager(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
        return auth;
    }
    // Инициализация шифратора паролей
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

}
