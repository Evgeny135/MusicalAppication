package org.application.musicalappication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
@PreAuthorize("permitAll()")
public class PublicController {

    @GetMapping("/welcome")
    public String welcome(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if (userDetails != null){
            if (userDetails.getAuthorities().contains("ALL")){
                model.addAttribute("username",userDetails.getUsername() + "aboba");
            }
            else {
                model.addAttribute("username",userDetails.getUsername());
            }
        }
        else {
            model.addAttribute("username", "Гость");
        }
        return "views/index";
    }
    @GetMapping("/about")
    public String about(){
        return "views/about";
    }
    @GetMapping("/news")
    public String news(){
        return "views/news";
    }
}
