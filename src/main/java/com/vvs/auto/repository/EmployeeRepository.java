package com.vvs.auto.repository;

import com.vvs.auto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(String id);

    Optional<Employee> findByMatricule(String matricule);

    Optional<Employee> findByName(String name);
}
