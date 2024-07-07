package org.application.musicalappication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class UserController {
    @GetMapping("{id}")
    public String getUserById(@PathVariable("id") int id, Model model){

        return "views/getUserById";
    }
}
