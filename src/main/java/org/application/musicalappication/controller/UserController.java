package org.application.musicalappication.controller;

import org.application.musicalappication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class UserController {
    ClientService clientService;
    @Autowired
    public UserController(ClientService clientService){
        this.clientService = clientService;
    }
    // Как сделать редирект на пользователя под которым вошли?
    // Как сделать что бы когда был произведен вход менялся шаблон хтмл
    // Нужно ли кастомные страницы входа и выхода?
    @GetMapping("{id}")
    public String getUserById(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails, Model model){
        clientService.getClientById(userDetails.getUsername());
        return "views/getUserById";
    }
}
