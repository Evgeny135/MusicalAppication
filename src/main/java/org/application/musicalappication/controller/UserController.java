package org.application.musicalappication.controller;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.service.ClientService;
import org.application.musicalappication.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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
        Client client = clientService.getClientById(id);
        Optional<List<Playlist>> playlists = playlistService.getPlaylistByClient(client.getId());
        model.addAttribute("client",client);
        model.addAttribute("playlists", playlists);
        return "views/userPage";
    }
}
