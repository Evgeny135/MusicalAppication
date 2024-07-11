package org.application.musicalappication.controller;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.ClientService;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.service.TrackService;
import org.application.musicalappication.stub.DataStubs;
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

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class UserController {
    ClientService clientService;
    PlaylistService playlistService;
    TrackService trackService;
    @Autowired
    public UserController(ClientService clientService, PlaylistService playlistService, TrackService trackService){
        this.clientService = clientService;
        this.playlistService = playlistService;
        this.trackService = trackService;
    }
    // Как сделать редирект на пользователя под которым вошли?
    // Как сделать что бы когда был произведен вход менялся шаблон хтмл
    // Нужно ли кастомные страницы входа и выхода?
    // Как лучше всего делать проверки на нулл значения

    // Добавить список музыки пользователя
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
        DataStubs dataStubs = new DataStubs();
        Client client = clientService.getClientById(id);
        List<Track> tracks = trackService.getTrackByClient(id).get();
        List<Playlist> albums = playlistService.getAlbumsByUser(id).get();
        List<Playlist> playlists = playlistService.getUserPlaylistsByUser(id).get();
        model.addAttribute("client",client);
        model.addAttribute("tracks", tracks);
        model.addAttribute("albums", albums);
        model.addAttribute("playlists", playlists);
        return "views/userPage";
    }
    @GetMapping("")
    public String redirectToUser(@AuthenticationPrincipal UserDetails userDetails, Model model){
        long id = clientService.getClientByEmail(userDetails.getUsername()).getId();
        return "redirect:/user/" + String.valueOf(id);
    }
}
