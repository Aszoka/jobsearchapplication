package com.hlxndr.jobsearchapplication.service;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.model.Position;
import com.hlxndr.jobsearchapplication.repository.ClientRepo;
import com.hlxndr.jobsearchapplication.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class JobSearchService {

    private final PositionRepo positionRepo;
    private final ClientRepo clientRepo;

    public UUID registerClient(ClientApp clientApp) {

        // check if email already registered
        boolean clientExist = clientRepo.findByEmail(clientApp.getEmail()).isPresent();


        if(clientExist) {
            throw new IllegalStateException("email already registered");
        }

        UUID clientApiKey = UUID.randomUUID();
        clientApp.setOwnApiKey(clientApiKey);
        clientRepo.save(clientApp);

        return clientApiKey;
    }

    private boolean isApiKeyExists(UUID apikey) {

        return clientRepo.findByOwnApiKey(apikey).isPresent();
    }


    public String addPosition(UUID apikey,Position position) {

        if(!isApiKeyExists(apikey)) {
            throw new IllegalStateException("Invalid apikey.");
        }
        positionRepo.save(position);

        Long id = position.getId();

        String url = "http://localhost:8081/api/v1/position/job?id=" + id;

        return url;
    }

    public Optional<Position> findPositionById(Long id) {
        return positionRepo.findById(id);
    }

    public List<Position> findByNameAndLocation(UUID apikey, String name, String location) {

        if(!isApiKeyExists(apikey)) {
            throw new IllegalStateException("Invalid apikey.");
        }

        Position position = new Position(name, location);

        return positionRepo
                .findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(position.getJobTitle(), position.getLocation());
    }

    public List<String> listJobUrls (List<Position> positions) {
        List<String> urls = new ArrayList<>();

        for(Position pos : positions) {
            String url = "http://localhost:8081/api/v1/position/job/" + pos.getId();
            urls.add(url);
        }
        return urls;
    }
}
