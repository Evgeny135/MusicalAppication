package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Track;
import org.application.musicalappication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.*;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final StorageService storageService;

    @Autowired
    public TrackService(TrackRepository trackRepository, StorageService storageService) {
        this.trackRepository = trackRepository;
        this.storageService=storageService;
    }

    public void loadTrack(Track track, Client client, MultipartFile file, long length){
        track.setAuthor(client);
        Time time = new Time(length*1000);
        time.setHours(0);
        track.setLength(time);
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

    public Optional<List<Track>> getTop100Tracks(){
        return trackRepository.getTop100Tracks();
    }

    public Optional<List<Track>> findTrackByKey(String key){
        Set<Track> trackSet = new LinkedHashSet<>();

        // By title
        trackSet.addAll(trackRepository.findTracksByTitle(key).orElse(new ArrayList<>()));
        // By author.name
        trackSet.addAll(trackRepository.findTracksByClientName(key).orElse(new ArrayList<>()));
        // By album
        trackSet.addAll(trackRepository.findTracksByAlbum(key).orElse(new ArrayList<>()));
        // By trackType.name
        trackSet.addAll(trackRepository.findTracksByTypeName(key).orElse(new ArrayList<>()));

        return Optional.of(trackSet.stream().toList());
    }
}
