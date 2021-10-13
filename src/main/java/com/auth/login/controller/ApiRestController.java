package com.auth.login.controller;

import java.util.*;

import com.auth.login.dao.EmpRepo;
import com.auth.login.exception.ResourceNotFound;
import com.auth.login.model.Emp;
import com.auth.login.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toolkit/")
public class ApiRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpRepo empRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    //@PreAuthorize("hasRole('employee')")
    @GetMapping("/home/{id}")
    public Emp getEmp(@PathVariable int id){
        return employeeService.getById(id);
    }

    //get employee by id
//    @GetMapping("/employees/{id}")
//    public ResponseEntity<Emp> getEmployeeById(@PathVariable int id){
//        Emp employee = empRepo.getById(id);
//        return ResponseEntity.ok(employee);
//    }

    @PreAuthorize("hasRole('employee')")
    @GetMapping("/employee")
    public ResponseEntity<String> greetingUser() {
        return new ResponseEntity<String>("Welcome, you have employee role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Business_Head')")
    @GetMapping("/businessHead")
    public ResponseEntity<String> greetingAdmin() {
        return new ResponseEntity<String>("Welcome, you have Business Head role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('manager')")
    @GetMapping("/manager")
    public ResponseEntity<String> greetingManager() {
        return new ResponseEntity<String>("Welcome, you have manager role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('scrum_master')")
    @GetMapping("/scrumMaster")
    public ResponseEntity<String> greetingScrumMaster() {
        return new ResponseEntity<String>("Welcome, you have scrum master role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('employee') or hasRole('manager')")
    @GetMapping("/employeeAndManager")
    public ResponseEntity<String> greetingUserOrAdmin() {
        return new ResponseEntity<String>("Welcome, you have employee and manager role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping("/anonymous")
    public ResponseEntity<String> greetingAnonymous() {
        return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
    }

}