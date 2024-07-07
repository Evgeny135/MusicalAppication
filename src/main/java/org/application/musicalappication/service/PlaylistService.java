package org.application.musicalappication.service;

import org.application.musicalappication.model.Playlist;
import org.application.musicalappication.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
