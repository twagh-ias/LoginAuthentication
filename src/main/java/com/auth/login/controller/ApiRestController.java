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

    @GetMapping("/home")
    public List<Emp> getAll(){
//        String user=request.getUserPrincipal().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
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

    @GetMapping("/getTeams/{username}")
    public List<Emp> getTeams(@PathVariable String username){
        return employeeService.findAllTeams(username);
    }

    @GetMapping("/getTeamSkill/{username}")
    public List<UserSkill> findAllTeamSkills(String username){
        return userSkillRepo.findAllTeamSkills(username);
    }

    @GetMapping("/getEmpDetails/{username}")
    public List<Emp> getEmpDetails(@PathVariable String username){
        return employeeService.findEmpDetails(username);
    }

    @GetMapping("/getSkillDetails/{username}")
    public List<UserSkill> getSkillDetails(@PathVariable String username){
        return userSkillService.findSkillDetails(username);
    }

    @PostMapping("/addEmp")
    public void addEmployee(Emp emp){
        employeeService.save(emp);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmp(@PathVariable int id){
        employeeService.delete(id);
    }

    @PutMapping("/updateEmp/{id}")
    public void updateEmp(Emp emp, @PathVariable long id){
        employeeService.update(emp,id);
    }

    @PostMapping("/addUserSkills")
    public void addUserSkills(UserSkill userSkill){
        userSkillService.insertUserSkill(userSkill);
    }

    @PutMapping("/updateUserSkills/{id}")
    public void updateUserSkills(UserSkill userSkill, @PathVariable long id){
        userSkillService.update(userSkill,id);
    }

    @DeleteMapping("/deleteUserSkills/{id}")
    public void deleteUserSkill(@PathVariable int id){
        userSkillService.deleteUserSkill(id);
    }

    @GetMapping("/roadmapSkills")
    public List<Roadmap_skills> getRoadmapAll(){
        return roadmapSkillService.findAllDetails();
    }

    @PostMapping("/validateSkillRoadmap")
    public HashMap<Integer,String> validate(String required_skill,int min_req_rating,int complexity){
        return employeeService.validate(required_skill,min_req_rating,complexity);
    }

    @PostMapping("/addRoadmapSkill")
    public void insertRoadmapSkill(Roadmap_skills roadmap_skills){
        roadmapSkillService.save(roadmap_skills);
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