

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

    private Integer id;
    @NotNull(message = "id must not be null or blank")
    @Min(value = 0, message = "id must be greater than 0")

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

    private BigDecimal height;
    @NotNull(message = "Height must not be null or blank")
    @Min(value = 0, message = "height must be greater than 0")

    private Integer age;
    @NotNull(message = "Age must not be null or blank")
    @Min(value = 0, message = "Age must be greater than 0")

    private BigDecimal weight;
    
}
