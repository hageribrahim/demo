package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.GenericResponseDTO;
import com.example.demo.services.EmployeeService;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<GenericResponseDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        logger.info().message("add employee method").field("employee dto",employeeDTO).log();
        return new ResponseEntity<>(employeeService.addEmployee(employeeDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<GenericResponseDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        logger.info().message("update employee method").field("employee dto",employeeDTO).log();
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
    }

}
