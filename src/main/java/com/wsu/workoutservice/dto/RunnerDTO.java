

package com.wsu.workoutservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunnerDTO {

    

    private Integer id; 

    
    @NotBlank(message = "Email must not be null or blank")
    @Size(max = 25)
    private String email;

    
    /* */
    @NotBlank(message = "First Name must not be null or blank")
    @Size(max = 25)
    private String firstName;


    @NotBlank(message = "Last Name must not be null or blank")
    @Size(max = 50)
    private String lastName;
    
    
}
