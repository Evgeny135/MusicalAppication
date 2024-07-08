package org.application.musicalappication.service;

import org.application.musicalappication.model.Track;
import org.application.musicalappication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Optional<List<Track>> getTrackByClient(Long id){
        return trackRepository.getTracksByUserId(id);
    }

    public Optional<List<Track>> getTracksByPlaylist(Long id){
        return trackRepository.getTracksByPlaylist(id);
    }

    public Optional<Track> getTrackById(Long id){
        return trackRepository.getTrackById(id);
    }
}
