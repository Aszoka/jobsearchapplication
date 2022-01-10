package com.hlxndr.jobsearchapplication.dataloader;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.model.Position;
import com.hlxndr.jobsearchapplication.repository.ClientRepo;
import com.hlxndr.jobsearchapplication.repository.PositionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class Dataloader {

    @Bean
    public CommandLineRunner run(ClientRepo repo, PositionRepo posRepo) {
        return args -> {
            repo.save(new ClientApp("james@gmail.com","James", UUID.randomUUID()));
            repo.save(new ClientApp("jane@gmail.com","Jane", UUID.randomUUID()));
            List<ClientApp> allClients = repo.findAll();
            System.out.println("All clients in DB: " + allClients);

            posRepo.save(new Position("junior java developer", "Budapest"));
            posRepo.save(new Position("data analyst", "Vienna"));
            posRepo.save(new Position("graphic designer", "Budapest"));
            posRepo.save(new Position("senior java developer", "Budapest"));
            List<Position> poses = posRepo.findAll();
            System.out.println("poses right now: " + poses);

        };
    }
}
