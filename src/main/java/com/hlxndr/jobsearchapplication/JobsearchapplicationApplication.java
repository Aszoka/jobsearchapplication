package com.hlxndr.jobsearchapplication;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import com.hlxndr.jobsearchapplication.model.Position;
import com.hlxndr.jobsearchapplication.repository.ClientRepo;
import com.hlxndr.jobsearchapplication.repository.PositionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JobsearchapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsearchapplicationApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ClientRepo repo, PositionRepo posRepo) {
		return args -> {
			repo.save(new ClientApp("james@gmail.com","James"));
			repo.save(new ClientApp("jane@gmail.com","Jane"));
			List<ClientApp> allClients = repo.findAll();
			System.out.println("All clients in DB: " + allClients);

			posRepo.save(new Position("junior java developer", "Budapest"));
			List<Position> poses = posRepo.findAll();
			System.out.println("poses right now: " + poses);
			
		};
	}

}
