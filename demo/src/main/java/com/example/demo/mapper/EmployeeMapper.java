package com.example.demo.mapper;

import com.example.demo.config.MapStructConfig;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

@Component
@Mapper(config = MapStructConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EmployeeMapper {



    public abstract Employee map(EmployeeDTO employeeDTO);
}
