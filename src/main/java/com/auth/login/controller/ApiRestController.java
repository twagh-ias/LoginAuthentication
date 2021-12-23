package com.auth.login.controller;
import java.util.*;
import com.auth.login.dao.EmpRepo;
import com.auth.login.dao.RoadmapSkillRepo;
import com.auth.login.dao.UserSkillRepo;
import com.auth.login.model.Emp;
import com.auth.login.model.Roadmap_skills;
import com.auth.login.model.UserSkill;
import com.auth.login.service.EmployeeService;
import com.auth.login.service.RoadmapSkillService;
import com.auth.login.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toolkit/")
@CrossOrigin("http://localhost:3000/")

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

    @Autowired
    RoadmapSkillService roadmapSkillService;

    @Autowired
    RoadmapSkillRepo roadmapSkillRepo;

//    get all employees from employee table
    @GetMapping("/home")
    public List<Emp> getAll(){
        return employeeService.findAllEmp();
    }

//    get skills of all employees from user_skills table
    @GetMapping("/home2")
    public List<UserSkill> getAllSkills(){
        return userSkillService.findAllSkills();
    }

//    get employee by id
    @GetMapping("/home/{id}")
    public Emp getEmp(@PathVariable int id){
        return employeeService.getById(id);
    }

//    get skill by id
    @GetMapping("/home2/{id}")
    public UserSkill getUserSkill(@PathVariable int id){
        return userSkillService.getById(id);
    }

//    get individual employee detail from employee table
    @GetMapping("/getEmpDetails")
    public List<Emp> getEmpDetails(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return employeeService.findEmpDetails(currentPrincipalName);
    }

//    get individual skill detail from user_skills table
    @GetMapping("/getSkillDetails")
    public List<UserSkill> getSkillDetails(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userSkillService.findSkillDetails(currentPrincipalName);
    }

//    get team members of employee who is logged in
    @GetMapping("/getTeams")
    public List<Emp> getTeams(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return employeeService.findAllTeams(currentPrincipalName);
    }

//    get skills of team members
    @GetMapping("/getTeamSkill")
    public List<UserSkill> findAllTeamSkills(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userSkillRepo.findAllTeamSkills(currentPrincipalName);
    }

//    add new employee details
    @PostMapping("/addEmp")
    public void addEmployee(Emp emp){
        employeeService.save(emp);
    }

//    delete employee by id
    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmp(@PathVariable int id){
        employeeService.delete(id);
    }

//    update employee details by id
    @PutMapping("/updateEmp/{id}")
    public void updateEmp(Emp emp, @PathVariable long id){
        employeeService.update(emp,id);
    }

//    add new user skills
    @PostMapping("/addUserSkills")
    public void addUserSkills(UserSkill userSkill){
        userSkillService.insertUserSkill(userSkill);
    }

//    update user skills for specific employee
    @PutMapping("/updateUserSkills/{id}")
    public void updateUserSkills(UserSkill userSkill, @PathVariable long id){
        userSkillService.update(userSkill,id);
    }

//    delete skill of an employee by id
    @DeleteMapping("/deleteUserSkills/{id}")
    public void deleteUserSkill(@PathVariable int id){
        userSkillService.deleteUserSkill(id);
    }

//    get roadmap skills
    @GetMapping("/roadmapSkills")
    public List<Roadmap_skills> getRoadmapAll(){
        return roadmapSkillService.findAllDetails();
    }

//  validate skill roadmap inputs
    @PostMapping("/validateSkillRoadmap")
    public HashMap<Integer,String> validate(String required_skill,int min_req_rating,int complexity){
        return employeeService.validate(required_skill,min_req_rating,complexity);
    }

//   add new roadmap skill
    @PostMapping("/addRoadmapSkill")
    public void insertRoadmapSkill(Roadmap_skills roadmap_skills){
        roadmapSkillService.save(roadmap_skills);
    }


//Role based logins
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