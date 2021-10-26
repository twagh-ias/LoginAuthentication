package com.auth.login.controller;

import java.util.*;

import com.auth.login.dao.EmpRepo;
import com.auth.login.dao.UserSkillRepo;
import com.auth.login.model.Emp;
import com.auth.login.model.UserSkill;
import com.auth.login.service.EmployeeService;
import com.auth.login.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toolkit/")
public class ApiRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpRepo empRepo;

    @Autowired
    UserSkillService userSkillService;

    @Autowired
    UserSkillRepo userSkillRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/home")
    public List<Emp> getAll(){
        return employeeService.findAllEmp();
    }

    @GetMapping("/home2")
    public List<UserSkill> getAllSkills(){
        return userSkillService.findAllSkills();
    }

    @GetMapping("/home/{id}")
    public Emp getEmp(@PathVariable int id){
        return employeeService.getById(id);
    }

    @GetMapping("/home2/{id}")
    public UserSkill getUserSkill(@PathVariable int id){
        return userSkillService.getById(id);
    }

//    @GetMapping(value = "/home3/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<JSONObject> getAllDetails(@PathVariable int id){
//        Emp e=employeeService.getById(id);
//        UserSkill u=userSkillService.getById(id);
//        JSONObject jsonobj=new JSONObject();
//        jsonobj.put("employee",e);
//        jsonobj.put("skills",u);
//        return ResponseEntity.ok(jsonobj);
//    }

//    @GetMapping(value = "/home3/{id}")
//    public JSONObject getAllDetails(@PathVariable int id){
//        Emp e=employeeService.getById(id);
//        UserSkill u=userSkillService.getById(id);
//        JSONObject jsonobj=new JSONObject();
//        jsonobj.put("employee",e);
//        jsonobj.put("skills",u);
//        return jsonobj;
//    }

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