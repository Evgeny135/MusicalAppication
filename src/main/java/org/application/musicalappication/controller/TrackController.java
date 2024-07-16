package org.application.musicalappication.controller;


import com.mpatric.mp3agic.Mp3File;
import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.security.ClientDetails;
import org.application.musicalappication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/track")
public class TrackController {

    private final StorageService storageService;
    private final TrackService trackService;
    private final PlaylistService playlistService;

    private final TrackTypeService trackTypeService;

    private final ConvertFile convertFile;

    public static final int TOP_TRACKS_COUNT = 48;

    @Autowired
    public TrackController(
            StorageService storageService,
            TrackService trackService,
            PlaylistService playlistService,
            TrackTypeService trackTypeService,
            ConvertFile convertFile) {
        this.storageService = storageService;
        this.trackService = trackService;
        this.playlistService = playlistService;
        this.trackTypeService = trackTypeService;
        this.convertFile = convertFile;
    }

    @GetMapping("/{id}")
    public String getTrackById(@AuthenticationPrincipal ClientDetails userDetails, @PathVariable("id") long id, Model model) {
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

        List<Playlist> userPlaylists;
        userPlaylists = playlistService.getPlaylistByClient(userDetails.getClient().getId()).orElse(new ArrayList<Playlist>());
        userPlaylists = userPlaylists.stream().filter(p -> !p.getTracks().contains(track)).collect(Collectors.toList());
        model.addAttribute("userPlaylists",userPlaylists);
        model.addAttribute("track", track);
        model.addAttribute("playlists", playlists);
        return "views/track";
    }

    @PostMapping("addToPlaylist")
    public String addTrackToPlaylist(@RequestParam("playlistId") Long plId, @RequestParam("trackId") Long trackId, Model model){
        Playlist playlist = playlistService.getPlaylistById(plId).get();
        Track track = trackService.getTrackById(trackId).get();
        playlistService.addTrackOnPlaylist(playlist,track);
        return "redirect:/playlist/" + plId.toString();
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("track", new Track());
        model.addAttribute("trackTypes", trackTypeService.getAllTrackType().orElse(new ArrayList<>()));
        return "views/trackAdd";
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute Track track, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal ClientDetails clientDetails) throws IOException {
        try {

            Mp3File mp3File = new Mp3File(convertFile.convertMultipartFileToFile(file));
            convertFile.deleteTemplateFile();
            long durationInSeconds = mp3File.getLengthInSeconds();

            trackService.loadTrack(track, clientDetails.getClient(), file, durationInSeconds);
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

    @GetMapping("/search")
    public String findTrack(Model model){

        model.addAttribute("trackList",trackService.getTopTracks(TOP_TRACKS_COUNT).orElse(new ArrayList<>()));
        return "views/trackSearch";
    }

    @PostMapping("/search")
    public String findTrackByStr(@RequestParam("searchKey") String key, Model model){
        if (key.isEmpty()){
            model.addAttribute("trackList",trackService.getTopTracks(TOP_TRACKS_COUNT).orElse(new ArrayList<>()));
        }
        else{
            model.addAttribute("trackList", trackService.findTrackByKey(key).orElse(new ArrayList<>()));
        }

        return "views/trackSearch";
    }
}
