package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {




    Employee findByCode(String code);

    @Transactional
    @Modifying
    @Query("update Employee emp set emp.status = :status where emp.code = :code")
    void updateEmployeeStatus(Status status, String code);

}
