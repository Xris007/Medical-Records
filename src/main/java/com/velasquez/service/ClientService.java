package com.velasquez.service;

import com.velasquez.model.Client;
import com.velasquez.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public void save(Client client){
        clientRepository.save(client);
    }

    public void update(Client client){ clientRepository.save(client); }

    public void delete(Client client){
        clientRepository.delete(client);
    }

    public Client findById(String dni){
        return clientRepository.findById(dni).orElse(null);
    }

}
