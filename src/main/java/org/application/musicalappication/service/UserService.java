package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public Client getClientById(Long id){
        return repository.getClientById(id);
    }
}
