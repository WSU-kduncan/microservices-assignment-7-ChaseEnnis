package com.wsu.workoutservice.repository;

import com.wsu.workoutservice.model.Runner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RunnerRepository extends JpaRepository<Runner, Integer> {

    @Query(nativeQuery = true, value = 
    //"Select * From WorkoutDB.Runner"
    "Select r.runner_id AS id, r.email as email, r.first_name AS firstName, r.last_name AS lastName, "
            + "r.gender AS gender, r.height AS height, r.age AS age, r.weight AS weight "
            + "FROM Runner r WHERE :search IS NULL OR (r.firstName = :search)"
            )
            // I don't think I need a join here since they are in the same table
    Page<Object[]> findBySearch(String search, Pageable pageable);

    boolean existsByEmail(String email);

}
