package com.hlxndr.jobsearchapplication.repository;

import com.hlxndr.jobsearchapplication.model.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<ClientApp, String> {

    Optional<ClientApp> findByEmail(String email);
}
