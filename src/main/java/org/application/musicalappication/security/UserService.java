package org.application.musicalappication.security;

import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

// Сервис для предлоставления пользователей
public class UserService implements UserDetailsService {
    List<User> userList;
    public UserService(){
        userList = new ArrayList<>();
    }
    public void addUser(User user){
        userList.add(user);
    }
    public User getUserByEmail(String email){
        return userList.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.getUserByEmail(username);
    }
}
