package org.application.musicalappication.service;

import org.application.musicalappication.model.TrackType;
import org.application.musicalappication.repository.TrackTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackTypeService {

    private final TrackTypeRepository trackTypeRepository;

    @Autowired
    public TrackTypeService(TrackTypeRepository trackTypeRepository) {
        this.trackTypeRepository = trackTypeRepository;
    }

    public Optional<List<TrackType>> getAllTrackType(){
        return trackTypeRepository.getAllTrackType();
    }
}
