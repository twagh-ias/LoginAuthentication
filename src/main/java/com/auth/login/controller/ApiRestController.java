package com.auth.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    @PreAuthorize("hasRole('employee')")
    @GetMapping("/greet/employee")
    public ResponseEntity<String> greetingUser() {
        return new ResponseEntity<String>("Welcome, you have employee role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Business_Head')")
    @GetMapping("/greet/businessHead")
    public ResponseEntity<String> greetingAdmin() {
        return new ResponseEntity<String>("Welcome, you have Business Head role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('manager')")
    @GetMapping("/greet/manager")
    public ResponseEntity<String> greetingManager() {
        return new ResponseEntity<String>("Welcome, you have manager role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('scrum_master')")
    @GetMapping("/greet/scrumMaster")
    public ResponseEntity<String> greetingScrumMaster() {
        return new ResponseEntity<String>("Welcome, you have scrum master role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('employee') or hasRole('manager')")
    @GetMapping("/greet/employeeAndManager")
    public ResponseEntity<String> greetingUserOrAdmin() {
        return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ANONYMOUS')")
    @GetMapping("/greet/anonymous")
    public ResponseEntity<String> greetingAnonymous() {
        return new ResponseEntity<String>("Welcome, you have USER and ADMIN role", HttpStatus.OK);
    }

}