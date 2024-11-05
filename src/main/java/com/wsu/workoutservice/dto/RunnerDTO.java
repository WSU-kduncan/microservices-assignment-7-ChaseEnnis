

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

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunnerDTO {

    
    @NotNull(message = "id must not be null or blank")
    @Min(value = 0, message = "id must be greater than 0")
    private Integer id; 

    
    @NotBlank(message = "Email must not be null or blank")
    @Size(max = 25)
    private String email;

    
    @NotBlank(message = "First Name must not be null or blank")
    @Size(max = 25)
    private String firstName;


    @NotBlank(message = "Last Name must not be null or blank")
    @Size(max = 50)
    private String lastName;
    
   
    @NotBlank(message = "Gender must not be null or blank")
    @Size(max=10)
    private String gender;

    
    @NotNull(message = "Height must not be null or blank")
    @Min(value = 0, message = "height must be greater than 0")
    private BigDecimal height;

    
    @NotNull(message = "Age must not be null or blank")
    @Min(value = 0, message = "Age must be greater than 0")
    private Integer age;

    @NotNull(message = "Height must not be null or blank")
    @Min(value = 0, message = "height must be greater than 0")
    private BigDecimal weight;
    
}
