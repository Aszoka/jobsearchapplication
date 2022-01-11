package com.hlxndr.jobsearchapplication.service;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.repository.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ClientService {

    private final ClientRepo clientRepo;

    public UUID registerClient(ClientApp clientApp) {
        // Todo: email validation and max length validation
        // check if email already registered
        boolean clientExist = clientRepo.findByEmail(clientApp.getEmail()).isPresent();

        if(clientExist) {
            throw new IllegalStateException("email already registered");
        }

        clientRepo.save(clientApp);
        return UUID.randomUUID();
    }

}
