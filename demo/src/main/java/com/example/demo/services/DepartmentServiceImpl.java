package com.example.demo.services;

import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ServerError;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findDepartment(String code) throws Exception {

        logger.info().message("find the department by code").field("department code", code).log();
        Optional<Department> department=  departmentRepository.findByCode(code);
        if (department.isEmpty()) {
            throw new ApplicationException(
                    ServerError.CODE_INVALID_DEPARTMENT.getMessage(),
                    String.format("Invalid code Department", ServerError.CODE_INVALID_DEPARTMENT),
                    HttpStatus.BAD_REQUEST
            );
        }
         return department.get() ;
    }
}
