package com.artysh.storeapp.services;

import com.artysh.storeapp.models.Client;
import com.artysh.storeapp.repositories.ClientRepository;
import com.artysh.storeapp.security.ClientDetails;
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
        Optional<Client> client = clientRepository.findByUsername(username);

        if (client.isEmpty())
            throw new UsernameNotFoundException("Unfortunately, this client not found!");
        return new ClientDetails(client.get());
    }
}
