package org.application.musicalappication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    //Пример страницы с незащищенным доступом
    @GetMapping("/")
    public String home() {
        return "redirect:/public/welcome"; // Перенаправление на /public/welcome
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ или Юзер
    @GetMapping("secured/user")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    public String userInfo(){

        return "views/userPage";
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ
    @GetMapping("secured/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminInfo(){
        return "views/adminPage";
    }

}
