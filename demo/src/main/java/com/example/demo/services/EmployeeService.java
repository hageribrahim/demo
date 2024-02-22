package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.GenericResponseDTO;

public interface EmployeeService {

    GenericResponseDTO addEmployee(EmployeeDTO employeeDTO) throws Exception;

    GenericResponseDTO updateEmployee( EmployeeDTO employeeDTO) throws Exception;

}
