

package com.wsu.workoutservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunnerDTO {

    private Integer id;
    @NotBlank(message = "RunnerID must not be null or blank")
    @Size(max = 10)

    private String email;
    @NotBlank(message = "Email must not be null or blank")
    @Size(max = 25)

    private String firstName;
    @NotBlank(message = "First Name must not be null or blank")
    @Size(max = 25)

    private String lastName;
    @NotBlank(message = "Last Name must not be null or blank")
    @Size(max = 50)
    
    private String gender;
    @NotBlank(message = "Gender must not be null or blank")
    @Size(max=10)

    private Double height;
    @NotBlank(message = "Height must not be null or blank")
    @Size(max=100)

    private Integer age;
    @NotBlank(message = "Age must not be null or blank")
    @Size(max=100)

    private Double weight;
   
}
