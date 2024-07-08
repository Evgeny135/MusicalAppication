package org.application.musicalappication.controller;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;
    private final TrackService trackService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, TrackService trackService) {
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

//    @GetMapping("/playlist")
//    @PreAuthorize("hasAuthority('USER')")
//    @ResponseBody
//    public String playlist(@AuthenticationPrincipal ClientDetails clientDetails){
//        return playlistService.getPlaylistByClient(clientDetails.getClient().getId()).toString();
//    }


    @GetMapping("/playlist")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseBody
    public List<Playlist> playlist(@AuthenticationPrincipal ClientDetails clientDetails){
        return playlistService.getUserPlaylistsByUser(clientDetails.getClient().getId()).get();
    }
}
