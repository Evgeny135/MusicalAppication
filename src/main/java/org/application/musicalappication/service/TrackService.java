package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final StorageService storageService;

    @Autowired
    public TrackService(TrackRepository trackRepository, StorageService storageService) {
        this.trackRepository = trackRepository;
        this.storageService=storageService;
    }

    public void loadTrack(Track track, Client client, MultipartFile file){
        track.setAuthor(client);
        track.setTags(storageService.uploadFile("musicbucket",file));

        trackRepository.addNewTrack(track);
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
