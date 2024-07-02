package org.application.musicalappication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    //Пример страницы с незащищенным доступом
    @GetMapping("public/welcome")
    public String welcome(){
        return "welcome";
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ или Юзер
    @GetMapping("secured/user")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public String userInfo(){
        return "userPage";
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ
    @GetMapping("secured/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminInfo(){
        return "adminPage";
    }

}
