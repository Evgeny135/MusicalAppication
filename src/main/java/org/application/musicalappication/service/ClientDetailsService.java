package org.application.musicalappication.service;

import org.application.musicalappication.model.Client;
import org.application.musicalappication.repository.ClientRepository;
import org.application.musicalappication.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.getClientByEmail(username);

        if (client.isEmpty()){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new ClientDetails(client.get());
    }
}
