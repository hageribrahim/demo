package com.example.demo.mapper;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T05:53:23+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public Employee map(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        if ( employeeDTO.getCode() != null ) {
            employee.setCode( employeeDTO.getCode() );
        }
        if ( employeeDTO.getName() != null ) {
            employee.setName( employeeDTO.getName() );
        }
        if ( employeeDTO.getMobileNo() != null ) {
            employee.setMobileNo( employeeDTO.getMobileNo() );
        }
        if ( employeeDTO.getStatus() != null ) {
            employee.setStatus( employeeDTO.getStatus() );
        }
        if ( employeeDTO.getDepartment() != null ) {
            employee.setDepartment( employeeDTO.getDepartment() );
        }

        return employee;
    }
}
