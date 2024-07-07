package org.application.musicalappication.controller;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.security.ClientDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    //Пример страницы с незащищенным доступом
    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String home() {
        return "redirect:/public/welcome"; // Перенаправление на /public/welcome
    }
    @GetMapping("public/welcome")
    @PreAuthorize("permitAll()")
    public String welcome(){
        return "index";
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ или Юзер
    @GetMapping("secured/user")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @ResponseBody
    public Client userInfo(@AuthenticationPrincipal ClientDetails clientDetails){
        return clientDetails.getClient();
//        return "userPage";
    }
    //Пример страницы с доступом только для авторизованных пользователей имеющих роль Админ
    @GetMapping("secured/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminInfo(){
        return "adminPage";
    }

}
