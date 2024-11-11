package com.wsu.workoutservice.controller;

import org.hibernate.mapping.Map;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsu.workoutservice.dto.ServiceResponseDTO;
import com.wsu.workoutservice.dto.EvaluationDTO;
import com.wsu.workoutservice.dto.ServiceResponseDTO;
import com.wsu.workoutservice.dto.WorkoutDTO;
import com.wsu.workoutservice.model.Workout;
//import com.wsu.workoutservice.service.EvaluationService;
import com.wsu.workoutservice.service.RunnerService;
import com.wsu.workoutservice.service.WorkoutService;
import com.wsu.workoutservice.utilities.Constants;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static com.wsu.workoutservice.utilities.Constants.MESSAGE;
import static com.wsu.workoutservice.utilities.Constants.PAGE_COUNT;
import static com.wsu.workoutservice.utilities.Constants.RESULT_COUNT;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<ServiceResponseDTO> getWorkouts(
        @RequestParam(required = false) String search,
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer rpp,
        @RequestParam(required = false, defaultValue = "workoutID") String sortField,
        @RequestParam(required = false, defaultValue = Constants.DESC) String sortOrder)
    {
        Page<WorkoutDTO> workoutDTOPage = workoutService.get(search, sortField, sortOrder, page, rpp);
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "workouts retrieved successfully.", PAGE_COUNT,
        workoutDTOPage.getTotalPages(), RESULT_COUNT, workoutDTOPage.getTotalElements())).data(workoutDTOPage.getContent())
        .build(), HttpStatus.OK);
        
    }
 
    
    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createWorkout(@RequestBody @Valid WorkoutDTO workoutDTO) {
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "workout created successfully"))
                .data(workoutService.save(workoutDTO)).build(), HttpStatus.OK);
    }

    @PutMapping("/{workout_id}")
    public ResponseEntity<ServiceResponseDTO> updateWorkout(@PathVariable int workoutId, @RequestBody @Valid WorkoutDTO workoutDTO) {
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "workout updated successfully"))
                .data(workoutService.update(workoutId, workoutDTO)).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{workout_id}")
    public ResponseEntity<ServiceResponseDTO> deleteWorkout(@PathVariable int workoutId) {
        workoutService.delete(workoutId);
        return new ResponseEntity<>(
                ServiceResponseDTO.builder()
                        .meta(Map.of(MESSAGE, "Successfully deleted workout"))
                        .build(),
                HttpStatus.OK);
    }

}