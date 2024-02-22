package com.example.demo.dto;

import com.example.demo.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotBlank
    String name;

    @NotBlank
    String code;

    @NotBlank
    String mobileNo;

    @NotBlank
    String Status;

    @NotBlank
    Department department;
}
