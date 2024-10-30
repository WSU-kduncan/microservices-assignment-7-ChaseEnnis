

package com.wsu.workoutservice.service;

import com.wsu.workoutservice.dto.RunnerDTO;
import com.wsu.workoutservice.exception.DatabaseErrorException;
import com.wsu.workoutservice.exception.InvalidRequestException;
import com.wsu.workoutservice.model.Runner;
import com.wsu.workoutservice.repository.RunnerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


import static com.wsu.workoutservice.utilities.CommonUtils.sort;

@Service
@Slf4j
@RequiredArgsConstructor
public class RunnerService {

    private final RunnerRepository runnerRepository;


    /**
     * This method used for retrieve the paginated runners data based on global search
     * @param search - allow to type ahead search by firstName, lastName, city, state, workOrder status
     * @param page - page no.
     * @param rpp - result per page
     * @param sortField - field for sorting the result
     * @param sortOrder - specify order for sorting
     * @return - paginated runners
     */
   
    public Page<RunnerDTO> get(String search, String sortField, String sortOrder, Integer page, Integer rpp) {
        try {
            // Call the repository method that searches for runners
            Page<Object[]> runners = runnerRepository.findBySearch(search, PageRequest.of(page - 1, rpp, sort(sortField, sortOrder)));
            
            // Map the retrieved Runner entities to RunnerDTO objects
            return runners.map(runner -> RunnerDTO.builder()
                    .id((Integer) runner[0]). email((String) runner[1])
                    .firstName((String) runner[2]).lastName((String) runner[3])
                    .gender((String) runner[4]).height((BigDecimal)runner[5] )
                    .age((Integer) runner[6]).weight((BigDecimal)runner[7] )
                    .build());
        } catch (Exception e) {
            log.error("Failed to retrieve runners. search:{}, sortField:{}, sortOrder:{}, page:{}, rpp:{}. Exception:",
                    search, sortField, sortOrder, page, rpp, e);
            throw new DatabaseErrorException("Failed to retrieve runners.", e);
        }
    }

    /**
     * This method used for add new runner, if it's not exists
     * @param runnerDTO - payload that contains runner info
     * @return - saved runner entity class
     */
    
     public RunnerDTO save(RunnerDTO runnerDTO) {
        if (runnerRepository.existsByEmail(runnerDTO.getEmail())) {
            throw new InvalidRequestException("Runner already exist with this email.");
        }
        try {
            return convertToDTO(runnerRepository.save(convertToEntity(runnerDTO)));
        } catch (Exception e) {
            log.error("Failed to create new runner. Exception:", e);
            throw new DatabaseErrorException("Failed to create new runner", e);
        }
    }



     /**
     * This method used for convert Entity model class to DTO
     */
    public RunnerDTO convertToDTO(Runner runner) {
        return RunnerDTO.builder().id(runner.getId()).email(runner.getEmail())
                .firstName(runner.getFirstName())
                .lastName(runner.getLastName()).gender(runner.getGender())
                //.height(runner.getHeight())
                .age(runner.getAge())
                //.weight(runner.getWeight())
                .build();
    }

     /**
     * This method used for convert DTO to entity model class.
     */
    public Runner convertToEntity(RunnerDTO runnerDTO) {
        return Runner.builder()
                 .email(runnerDTO.getEmail())
                .firstName(runnerDTO.getFirstName())
                .lastName(runnerDTO.getLastName())
                .gender(runnerDTO.getGender())
                //.height(runnerDTO.getHeight())
                .age(runnerDTO.getAge())
                .build();
    }


    public RunnerDTO update(Integer id, RunnerDTO runnerDTO) {
        if (!runnerRepository.existsById(id)) {
            throw new InvalidRequestException("Invalid runner id.");
        }
        try {
            Runner runner = convertToEntity(runnerDTO);
            runner.setId(id);
            return convertToDTO(runnerRepository.save(runner));
        } catch (Exception e) {
            log.error("Failed to update runner, RunnerId:{}. Exception:{}", id, e);
            throw new DatabaseErrorException("Failed to update runner", e);
        }
    }

    public void delete(Integer id){
        if(!runnerRepository.existsById(id)){
            throw new InvalidRequestException("Invalid Runner ID");
        }
        try {
            runnerRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete Runner, runnerId:{}. Exception:{}", id, e);
            throw new DatabaseErrorException("Failed to delete runner", e);
        }
    }

}