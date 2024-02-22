package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.GenericResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ServerError;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public GenericResponseDTO addEmployee(EmployeeDTO employeeDTO) throws Exception {

        logger.info().message("add employee method").field("employee dto", employeeDTO).log();

        try {
            Department department = departmentService.findDepartment(employeeDTO.getDepartment().getCode());
            Employee employee = employeeMapper.map(employeeDTO);
            Employee checkCode = employeeRepository.findByCode(employeeDTO.getCode());
            if (checkCode !=null) {
                logger.error().message("code already taken")
                        .field("code already taken", employeeDTO).log();

                throw new ApplicationException(
                        ServerError.CODE_REPEATED_EMPLOYEE.getMessage(),
                        String.format("code already taken", ServerError.CODE_REPEATED_EMPLOYEE),
                        HttpStatus.BAD_REQUEST
                );
            }

            employee.setDepartment(department);
            employeeRepository.save(employee);
            return new GenericResponseDTO("Employee created.");

        } catch (Exception exception) {
            logger.error().message("failed to save employee")
                    .field("employee dto", employeeDTO)
                    .stack()
                    .exception("exception", exception)
                    .log();
            throw new ApplicationException(
                    ServerError.INTERNAL_SERVER_ERROR.getMessage(),
                    String.format("internal server error", ServerError.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public GenericResponseDTO updateEmployee(EmployeeDTO employeeDTO) throws Exception {

        logger.info().message("update employee method")
                .field("employee dto", employeeDTO).log();

        Employee employee = employeeRepository.findByCode(employeeDTO.getCode());
        if (Objects.isNull(employee)) {

            logger.error().message("Employee not found")
                    .field("employee dto", employeeDTO).log();

            throw new ApplicationException(
                    ServerError.EMPLOYEE_NOT_FOUND.getMessage(),
                    String.format("Employee not found", ServerError.EMPLOYEE_NOT_FOUND),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            Employee employer = employeeMapper.map(employeeDTO);
            employeeRepository.updateEmployeeStatus(employer.getStatus(), employer.getCode());
            return new GenericResponseDTO("Employee updated.");


        } catch (Exception exception) {
            logger.error().message("failed to update employee")
                    .field("employee dto", employeeDTO)
                    .stack()
                    .exception("exception", exception)
                    .log();

            throw new ApplicationException(
                    ServerError.INTERNAL_SERVER_ERROR.getMessage(),
                    String.format("internal server error", ServerError.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    private Employee findEmployeeByCode (String code){
//        Optional<Employee> employee= employeeRepository.findByCode(code);
//        return employee.get();
//    }
}
