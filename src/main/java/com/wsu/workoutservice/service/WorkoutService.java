package com.wsu.workoutservice.service;

import com.wsu.workoutservice.dto.WorkoutDTO;
import com.wsu.workoutservice.exception.DatabaseErrorException;
import com.wsu.workoutservice.exception.InvalidRequestException;
//import com.wsu.workorderproservice.exception.DatabaseErrorException;
//import com.wsu.workorderproservice.exception.InvalidRequestException;
import com.wsu.workoutservice.model.Workout;
import com.wsu.workoutservice.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



import static com.wsu.workoutservice.utilities.CommonUtils.sort;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Page<WorkoutDTO> get(String search, String sortField, String sortOrder, Integer page, Integer rpp) {
        try {
            Page<Object[]> workouts = workoutRepository.findBySearch(search, PageRequest.of(page-1, rpp, sort(sortField, sortOrder)));
            return workouts.map(workout -> WorkoutDTO.builder().workoutId((int) workout[0]).type((int) workout[1]).build());
        } catch (Exception e) {
            log.error("Failed to retrieve workouts. search:{}, sortField:{}, sortOrder:{}, page:{}, rpp:{}. Exception:",
                    search, sortField, sortOrder, page, rpp, e);
            throw new DatabaseErrorException("Failed to retrieve workouts.", e);
        }
    }

    public WorkoutDTO save(WorkoutDTO workoutDTO) {
        if (workoutRepository.existsById(workoutDTO.getWorkoutId())) {
            throw new InvalidRequestException("workout already exist with this workoutId.");
        }
        try {
            return convertToDTO(workoutRepository.save(convertToEntity(workoutDTO)));
        } catch (Exception e) {
            log.error("Failed to create new workout. Exception:", e);
            throw new DatabaseErrorException("Failed to create new workout", e);
        }
    }

    public WorkoutDTO update(Integer id, WorkoutDTO workoutDTO) {
        if (!workoutRepository.existsById(id)) {
            throw new InvalidRequestException("Invalid workout id.");
        }
        try {
            Workout workout = convertToEntity(workoutDTO);
            workout.setId(id);
            return convertToDTO(workoutRepository.save(workout));
        } catch (Exception e) {
            log.error("Failed to update workout, workoutId:{}. Exception:{}", id, e);
            throw new DatabaseErrorException("Failed to update workout", e);
        }
    }

    public Workout convertToEntity(WorkoutDTO workoutDTO) {
        return Workout.builder().id(workoutDTO.getWorkoutId()).type(workoutDTO.getType()).build();
    }

    public WorkoutDTO convertToDTO(Workout workout) {
        return WorkoutDTO.builder().workoutId(workout.getId()).type(workout.getType()).build();
    }

    public void delete(Integer id) {
        if (!workoutRepository.existsById(id)) {
            throw new InvalidRequestException("workout with ID " + id + " does not exist.");
        }
        try {
            workoutRepository.deleteById(id);
            log.info("Successfully deleted workout with ID {}", id);
        } catch (Exception e) {
            log.error("Failed to delete workout, workoutId:{}. Exception:{}", id, e);
            throw new DatabaseErrorException("Failed to delete workout", e);
        }
    }
}