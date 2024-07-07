package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.model.Role;
import org.application.musicalappication.repository.ClientRepository;
import org.application.musicalappication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public ClientService(ClientRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Client getClientById(String email){
        return repository.getClientByEmail(email).get();
    }

    public void register(Client client){
        Role role = roleRepository.getRoleByName("USER").get();
        client.setRole(role);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        repository.register(client);
    }
}
