package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Optional<List<Playlist>> getPlaylistByClient(Long id){
        return playlistRepository.getPlaylistByClient(id);
    }
    public Optional<List<Playlist>> getPlaylistsByTrack(Long id) {
        return playlistRepository.getPlaylistByTrack(id);
    }
    public Optional<List<Playlist>> getAlbumsByUser(Long id){
        return playlistRepository.getAlbumsByUser(id);
    }

    public Optional<List<Playlist>> getUserPlaylistsByUser(Long id){
        return playlistRepository.getUserPlaylistsByUser(id);
    }

    public int getPlaylistSize(Long id){
        return playlistRepository.getPlaylistByClient(id).orElse(new ArrayList<>()).size();
    }

    public Optional<Playlist> getPlaylistById(Long id){
        return playlistRepository.getPlaylistById(id);
    }

    public void addPlaylist(Playlist playlist, Client client){
        playlist.setClient(client);
        playlistRepository.addPlaylist(playlist);
    }

}
