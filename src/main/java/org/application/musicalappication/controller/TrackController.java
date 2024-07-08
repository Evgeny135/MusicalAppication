package org.application.musicalappication.controller;

import org.application.musicalappication.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class TrackController {

    private final StorageService storageService;

    @Autowired
    public TrackController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public String uploadForm(Model model){
        return "upload";
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String bucketName = "musicbucket";

        try {
            String eTag = storageService.uploadFile(bucketName, file);
            return new ResponseEntity<>("File uploaded successfully with eTag: " + eTag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
