package org.application.musicalappication.controller;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.service.PlaylistTypeService;
import org.application.musicalappication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/playlist")
@PreAuthorize("permitAll()")
public class PlaylistController {

    PlaylistService playlistService;
    TrackService trackService;
    PlaylistTypeService playlistTypeService;

    public static final int TOP_PLAYLISTS_COUNT = 48;

    @Autowired
    public PlaylistController(PlaylistService playlistService, TrackService trackService,PlaylistTypeService playlistTypeService){
        this.playlistService = playlistService;
        this.trackService = trackService;
        this.playlistTypeService = playlistTypeService;
    }
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String playlistAdd(Model model){
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("playlistTypes", playlistTypeService.getAllPlaylistType().orElse(new ArrayList<>()));
        return "views/playlistAdd";
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public String playlistAdd(
            @ModelAttribute("playlist") Playlist playlist,
            @AuthenticationPrincipal ClientDetails clientDetails,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        try {
            playlistService.addPlaylist(playlist,clientDetails.getClient());
            redirectAttributes.addFlashAttribute("successMessage", "Плейлист успешно добавлен");
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка добавления плейлиста: " + e.getMessage());
        }

            return "redirect:/playlist/add";
    }
    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable("id") long id , Model model){
        Playlist playlist;

        playlist = playlistService.getPlaylistById(id).orElse(new Playlist());

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

    @GetMapping("/search")
    public String playlistList(Model model){

        model.addAttribute("playlistList", playlistService.getTopPlaylists(TOP_PLAYLISTS_COUNT).orElse(new ArrayList<>()));
        return "views/playlistSearch";
    }

    @PostMapping("/search")
    public String playlistSearchList(@RequestParam("playlistSearchKey") String key, Model model){
        if (key.isEmpty()){
            model.addAttribute("playlistList",playlistService.getTopPlaylists(TOP_PLAYLISTS_COUNT).orElse(new ArrayList<>()));
        }
        else{
            model.addAttribute("playlistList", playlistService.findPlaylistsByKey(key).orElse(new ArrayList<>()));
        }
        return "views/playlistSearch";
    }
}
