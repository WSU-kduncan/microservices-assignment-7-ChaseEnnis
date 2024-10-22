package com.wsu.workoutservice.model;

import java.text.DecimalFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "runner")
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runnerID")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private DecimalFormat addressLine2;

    @Column(name = "age")
    private Integer city;

    @Column(name = "weight")
    private DecimalFormat state;

}