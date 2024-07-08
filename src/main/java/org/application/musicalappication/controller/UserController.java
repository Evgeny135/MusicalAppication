package org.application.musicalappication.controller;

import org.application.musicalappication.service.ClientService;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.stub.DataStubs;
import org.springframework.beans.factory.annotation.Autowired;
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
    ClientService clientService;
    PlaylistService playlistService;
    // MusicService musicService;
    @Autowired
    public UserController(ClientService clientService, PlaylistService playlistService){
        this.clientService = clientService;
        this.playlistService = playlistService;
    }
    // Как сделать редирект на пользователя под которым вошли?
    // Как сделать что бы когда был произведен вход менялся шаблон хтмл
    // Нужно ли кастомные страницы входа и выхода?
    // Как лучше всего делать проверки на нулл значения

    // Добавить список музыки пользователя
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
        DataStubs dataStubs = new DataStubs();
        model.addAttribute("client",dataStubs.client);
        model.addAttribute("tracks", dataStubs.tracks);
        model.addAttribute("albums", dataStubs.albums);
        model.addAttribute("playlists", dataStubs.playlists);
        return "views/userPage";
    }
}
