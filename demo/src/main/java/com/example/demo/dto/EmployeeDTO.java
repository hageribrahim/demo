package com.example.demo.dto;

import com.example.demo.entity.Department;
import com.example.demo.entity.Status;
import lombok.Data;

@Data
public class EmployeeDTO {

    String name;

    String mobileNo;

    String code;
    Status status;

    Department department;

}
