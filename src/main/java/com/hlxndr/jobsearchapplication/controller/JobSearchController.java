package com.hlxndr.jobsearchapplication.controller;

import com.hlxndr.jobsearchapplication.dto.ClientDTO;
import com.hlxndr.jobsearchapplication.dto.PositionDTO;
import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.model.Position;
import com.hlxndr.jobsearchapplication.service.JobSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Validated
public class JobSearchController {

    private final JobSearchService jobSearchService;

    @PostMapping("/clients")
    public ResponseEntity<UUID> registerClient( @RequestBody @Valid ClientDTO clientDTO) {
        return new ResponseEntity<>(jobSearchService.registerClient(clientDTO), HttpStatus.CREATED);
    }

    @PostMapping("/positions")
    public ResponseEntity<String> addPosition(@RequestParam UUID apikey,
                                              @Valid @RequestBody PositionDTO positionDTO) {
        return new ResponseEntity<>(
                jobSearchService.addPosition(apikey, positionDTO),
                HttpStatus.CREATED);
    }

    /**
     * after registering a new position, the response gives back an url composed with the given job's id
     * this is the endpoint for that link and the job can be checked
     * @param id
     * @return
     */
    @GetMapping(path ="/position/job")
    public ResponseEntity<Optional<Position>> addedPosition(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                jobSearchService.findPositionById(id),
                HttpStatus.OK);
    }

    @GetMapping(path ="/search")
    public ResponseEntity<List<String>> listSearchedPositions(@RequestParam UUID apikey,
                                                              @RequestParam @Valid String jobTitle,
                                                              @RequestParam @Valid String location) {

      @Valid PositionDTO positionDTO = new PositionDTO(
                jobTitle,
                location
        );
        return new ResponseEntity<>(
                jobSearchService.listJobUrls(jobSearchService.findByNameAndLocation(apikey,positionDTO)),
                HttpStatus.OK) ;
    }
}
