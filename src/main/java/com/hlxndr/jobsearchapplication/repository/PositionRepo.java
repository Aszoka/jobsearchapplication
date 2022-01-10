package com.hlxndr.jobsearchapplication.repository;

import com.hlxndr.jobsearchapplication.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepo extends JpaRepository<Position, Long> {

    List<Position> findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String jobTitle, String location);
}
