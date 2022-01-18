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
            ClientApp client1 = new ClientApp("testcompany1@gmail.com","testname1", UUID.randomUUID());
            ClientApp client2 = new ClientApp("testcompany2@gmail.com","testname2", UUID.randomUUID());
            repo.save(client1);
            repo.save(client2);

            List<ClientApp> allClients = repo.findAll();
            System.out.println("All clients in DB: " + allClients);

            posRepo.save(new Position("junior java developer", "Budapest", client1));
            posRepo.save(new Position("data analyst", "Vienna", client2));
            posRepo.save(new Position("graphic designer", "Budapest", client1));
            posRepo.save(new Position("senior java developer", "Budapest", client2));
            List<Position> poses = posRepo.findAll();
            System.out.println("poses right now: " + poses);

        };
    }
}