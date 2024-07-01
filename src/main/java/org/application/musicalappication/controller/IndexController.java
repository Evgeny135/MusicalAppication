package org.application.musicalappication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    // start page
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
