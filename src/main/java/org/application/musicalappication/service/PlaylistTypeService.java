package org.application.musicalappication.service;

import org.application.musicalappication.model.PlaylistType;
import org.application.musicalappication.repository.PlaylistTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistTypeService {
    private final PlaylistTypeRepository playlistTypeRepository;

    @Autowired
    public PlaylistTypeService(PlaylistTypeRepository playlistTypeRepository) {
        this.playlistTypeRepository = playlistTypeRepository;
    }

    public Optional<List<PlaylistType>> getAllPlaylistType(){
        return playlistTypeRepository.getAllTrackType();
    }
}
