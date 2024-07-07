package org.application.musicalappication.controller;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private final ClientService clientService;
    public RegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute Client client) {
        clientService.register(client);
        return "redirect:/public/welcome";
    }
}
