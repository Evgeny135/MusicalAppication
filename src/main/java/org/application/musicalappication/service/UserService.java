package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ClientRepository repository;
    public Client getClientById(String email){
        return repository.getClientByEmail(email).get();
    }
}
