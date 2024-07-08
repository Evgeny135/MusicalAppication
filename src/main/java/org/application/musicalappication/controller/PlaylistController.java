package org.application.musicalappication.controller;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlist")
    @PreAuthorize("hasAuthority('USER')")
    public String playlist(@AuthenticationPrincipal ClientDetails clientDetails, Model model){
        List<Playlist> playlists = playlistService.getPlaylistByClient(clientDetails.getClient().getId()).orElse(new ArrayList<>());
        model.addAttribute("playlists",playlists);
        return "playlistPage";
    }
}
