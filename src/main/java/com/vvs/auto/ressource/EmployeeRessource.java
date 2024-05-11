package com.vvs.auto.ressource;

import com.vvs.auto.model.Employee;
import com.vvs.auto.model.Vehicule;
import com.vvs.auto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRessource {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Employee>((Employee) employeeRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json"})
    ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody(required = true) Employee employee) {
        Employee empl = employeeRepository.findById(id).map(e -> {
            e.setMatricule(employee.getMatricule());
            e.setRecrutementDate(employee.getRecrutementDate());
            e.setName(employee.getName());
            return employeeRepository.save(e);
        }).orElseGet(() -> {
            Employee e = new Employee();
            e.setMatricule(employee.getMatricule());
            e.setRecrutementDate(employee.getRecrutementDate());
            e.setName(employee.getName());
                    return employeeRepository.save(e);
                }
        );
        return ResponseEntity.ok(empl);
    }
}
