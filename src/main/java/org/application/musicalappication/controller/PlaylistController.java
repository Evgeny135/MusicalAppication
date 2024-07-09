package org.application.musicalappication.controller;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/playlist")
@PreAuthorize("permitAll()")
public class PlaylistController {

    PlaylistService playlistService;
    TrackService trackService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, TrackService trackService){
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable("id") long id , Model model){
        Playlist playlist;
        if (playlistService.getPlaylistById(id).isPresent()){
            playlist = playlistService.getPlaylistById(id).get();
        }
        else{
            playlist = new Playlist();
        }
        List<Track> tracks;

        if (playlist.getTracks() != null){
            tracks = playlist.getTracks();
        }
        else{
            tracks = new LinkedList<>();
        }

        model.addAttribute("playlist", playlist);
        model.addAttribute("tracks", tracks);
        return "views/playlist";
    }
}
