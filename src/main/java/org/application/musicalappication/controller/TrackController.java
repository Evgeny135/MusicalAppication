package org.application.musicalappication.controller;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.model.TrackType;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.PlaylistService;
import org.application.musicalappication.service.StorageService;
import org.application.musicalappication.service.TrackService;
import org.application.musicalappication.service.TrackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/track")
public class TrackController {

    private final StorageService storageService;
    private final TrackService trackService;
    private final PlaylistService playlistService;

    private final TrackTypeService trackTypeService;


    @Autowired
    public TrackController(StorageService storageService, TrackService trackService, PlaylistService playlistService, TrackTypeService trackTypeService) {
        this.storageService = storageService;
        this.trackService = trackService;
        this.playlistService = playlistService;
        this.trackTypeService = trackTypeService;
    }

    @GetMapping("/{id}")
    public String getTrackById(@PathVariable("id") long id, Model model) {
        Track track;
        List<Playlist> playlists;
        if (trackService.getTrackById(id).isPresent()) {
            track = trackService.getTrackById(id).get();
        } else {
            track = new Track();
        }
        if (playlistService.getPlaylistsByTrack(id).isPresent()) {
            playlists = playlistService.getPlaylistsByTrack(id).get();
        } else {
            playlists = new ArrayList<>();
        }
        model.addAttribute("track", track);
        model.addAttribute("playlists", playlists);
        return "views/track";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("track", new Track());
        model.addAttribute("trackTypes", trackTypeService.getAllTrackType().orElse(new ArrayList<>()));
        return "views/trackAdd";
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(TrackType trackType,@ModelAttribute Track track,@RequestParam(value = "file",required = false) MultipartFile file, @AuthenticationPrincipal ClientDetails clientDetails) {
//        Track track = (Track)model.getAttribute("track");
        track.getTitle();
        try {
            trackService.loadTrack(track, clientDetails.getClient(), file);
            return new ResponseEntity<>("Музыка успешно загружна", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка при загрузке", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/audio")
    public String getAudio(@RequestParam String key, Model model) {
        model.addAttribute("bucketName", "musicbucket");
        model.addAttribute("key", key);
        return "audio";
    }

    @GetMapping("/audio/download")
    public ResponseEntity<ByteArrayResource> music(@RequestParam String key) {
        byte[] data = storageService.loadFile("musicbucket", key);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/tracks")
    public String tracks(@RequestParam(required = false) String key, @AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        List<Track> trackList = trackService.getTrackByClient(clientDetails.getClient().getId()).get();
        model.addAttribute("tracks", trackList);
        model.addAttribute("selectedTrack", key);
        return "player";
    }


}
