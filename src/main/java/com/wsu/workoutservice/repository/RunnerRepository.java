package com.wsu.workoutservice.repository;

import com.wsu.workoutDB.model.Runner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RunnerRepository extends JpaRepository<Runner, Integer> {

    @Query(nativeQuery = true, value = "Select r.runnerID AS id, r.firstName AS firstName, r.lastName AS lastName, "
            + "r.gender AS gender, r.height AS height, r.age AS age, r.weight AS weight, "
            + "FROM Runner r "
            + "ORDER BY r.lastName")
            // I don't think I need a join here since they are in the same table
    Page<Object[]> findBySearch(String search, Pageable pageable);

    boolean existsByEmail(String email);

}
