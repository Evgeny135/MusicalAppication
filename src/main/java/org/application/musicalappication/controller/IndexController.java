package org.application.musicalappication.controller;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public Client index(@RequestParam Long id){
        return userService.getClientById(id);
    }

    @GetMapping("/test")
    public String test(){
        return "authorization";
    }
}
