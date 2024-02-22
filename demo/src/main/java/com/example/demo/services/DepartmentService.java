package com.example.demo.services;

import com.example.demo.entity.Department;

public interface DepartmentService {

    Department findDepartment(String code) throws Exception;
}
