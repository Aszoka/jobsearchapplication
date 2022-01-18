package com.hlxndr.jobsearchapplication.service;

import com.hlxndr.jobsearchapplication.dto.ClientDTO;
import com.hlxndr.jobsearchapplication.dto.PositionDTO;
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

    public UUID registerClient(ClientDTO clientDTO) {

        ClientApp clientApp = new ClientApp(
                clientDTO.getEmail(),
                clientDTO.getName()
        );

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


    public String addPosition(UUID apikey, PositionDTO positionDTO) {


        if(!isApiKeyExists(apikey)) {
            throw new IllegalStateException("Invalid apikey.");
        }

        Optional<ClientApp> postedByOptional = clientRepo.findByOwnApiKey(apikey);
        ClientApp postedBy = new ClientApp();

        if(postedByOptional.isPresent()) {
            postedBy = postedByOptional.get();
        }

        Position position = new Position(
                positionDTO,
                postedBy
        );

            positionRepo.save(position);

            Long id = position.getId();

            String url = "http://localhost:8081/api/v1/position/job?id=" + id;


        return url;
    }

    public Optional<Position> findPositionById(Long id) {
        return positionRepo.findById(id);
    }

    private List<Position> findByNameAndLocation(UUID apikey, PositionDTO positionDTO) {

        if(!isApiKeyExists(apikey)) {
            throw new IllegalStateException("Invalid apikey.");
        }

        Position position = new Position(positionDTO);

        return positionRepo
                .findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(
                        position.getJobTitle(), position.getLocation());
    }

    public List<String> listJobUrls (UUID apikey, PositionDTO positionDTO) {
        List<Position> positions = findByNameAndLocation(apikey, positionDTO);
        List<String> urls = new ArrayList<>();

        for(Position pos : positions) {
            String url = "http://localhost:8081/api/v1/position/job/" + pos.getId();
            urls.add(url);
        }
        return urls;
    }
}
