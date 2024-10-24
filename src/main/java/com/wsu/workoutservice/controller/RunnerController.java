
package com.wsu.workoutservice.controller;

import com.wsu.workoutservice.dto.ServiceResponseDTO;
import com.wsu.workoutservice.dto.RunnerDTO;
import com.wsu.workoutservice.service.RunnerService;
import com.wsu.workoutservice.utilities.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.Map;



import static com.wsu.workoutservice.utilities.Constants.MESSAGE;
import static com.wsu.workoutservice.utilities.Constants.PAGE_COUNT;
import static com.wsu.workoutservice.utilities.Constants.RESULT_COUNT;


/**
 * This controller class created for Runner resource CRUD endpoints
 */
@RestController
@RequestMapping("/runners")
@RequiredArgsConstructor
public class RunnerController {

    private final RunnerService runnerService;

    /**
     * This method used for retrieve the Runners based on given pagination and filter
     * @param search - allow to filter by customer firstName, lastName, city, state, workOrder status
     * @param page - no. of page (default 1)
     * @param rpp - results per page (default 10)
     * @param sortField - sort field for sorting the results (default dateLastUpdated)
     * @param sortOrder - sort order (default desc)
     * @return - list of customers that's matched given criteria
     */
    @GetMapping
    public ResponseEntity<ServiceResponseDTO> getRunners(@RequestParam(required = false) String search,
                                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer rpp,
                                                  @RequestParam(required = false, defaultValue = "dateLastUpdated") String sortField,
                                                  @RequestParam(required = false, defaultValue = Constants.DESC) String sortOrder) {
        Page<RunnerDTO> runnerDTOPagePage = runnerService.get(search, sortField, sortOrder, page, rpp);
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "Runners retrieved successfully.", PAGE_COUNT,
                runnerDTOPagePage.getTotalPages(), RESULT_COUNT, runnerDTOPagePage.getTotalElements())).data(runnerDTOPagePage.getContent())
                .build(), HttpStatus.OK);
    }



    /**
     * This method used for add new runner info if it's not exists
     * @param runnerDTO - payload that contains runner info
     * @return - saved runner with HTTP status
     */
    
    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createRunner(@RequestBody @Valid RunnerDTO runnerDTO) {
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "Runner created successfully"))
                .data(runnerService.save(runnerDTO)).build(), HttpStatus.OK);
    }
    

    /**
     * This method used for update runner by runner id
     * @param id - id of runner to update
     * @param runnerDTO -  payload that contains runner info that need to be updated
     * @return - updated runner with HTTP status
     */

     
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateRunner(@PathVariable Integer id, @RequestBody @Valid RunnerDTO runnerDTO) {
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(MESSAGE, "Runner updated successfully"))
              . data(runnerService.update(id, runnerDTO)).build(), HttpStatus.OK);
    }

    
    /**
     * This method used to delete Runner by runner id
     *  @param id - id thats used tp delete runner
     *  @return - http status of deleted Runner 
     */

    

     @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> deleteRunner(@PathVariable Integer id) {
         runnerService.delete(id);
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of("MESSAGE", "Successfully deleted runner"))
        .build(), HttpStatus.OK);  // Include the HTTP status here
    }


     

}
