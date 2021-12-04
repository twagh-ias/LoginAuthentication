package com.auth.login.dao;

import com.auth.login.model.Emp;
import com.auth.login.rowmapper.EmpMapper;
import com.auth.login.vo.UserVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

@Repository
public class EmpRepoImpl implements EmpRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String get_emp_query="SELECT employee_name,email,department,org_level,team,total_exp," +
            "ad_tech_exp,slack_time,certifications,role,projects from employee where e_id=?";

    private static final String get_emp_all="select * from employee";

    private static final String insert_query="insert into employee(employee_name,team,designation,role,email,org_level,projects,department,total_exp,ad_tech_exp,slack_time,certifications,password) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String update_query = "update employee set employee_name = ?, email = ?,total_exp = ?,ad_tech_exp = ?," +
            "slack_time = ?,certifications = ?,team = ?, designation = ?, role = ?, org_level=?,projects=?,department=? where e_id = ?";

    private static final String delete_skill_query="delete from user_skills where e_id=?";
    private static final String delete_emp_query = "delete from employee where e_id = ?";
    private static final String get_emp_details="select * from employee where email=?";

    @Override
    public Emp getById(int e_id) {
        return jdbcTemplate.queryForObject(get_emp_query,
                new Object[]{e_id},
                (rs, rowNum) -> {
                    Emp e = new Emp();
                    e.setEmployee_name(rs.getString("employee_name"));
                    e.setEmail(rs.getString("email"));
                    e.setDepartment(rs.getString("department"));
                    e.setOrg_level(rs.getString("org_level"));
                    e.setTeam(rs.getString("team"));
                    e.setTotal_exp(rs.getString("total_exp"));
                    e.setAd_tech_exp(rs.getString("ad_tech_exp"));
                    e.setSlack_time(rs.getString("slack_time"));
                    e.setCertifications(rs.getString("certifications"));
                    e.setRole(rs.getString("role"));
                    e.setProjects(rs.getString("projects"));
                    return e;
                });
    }

    @Override
    public List<Emp> findAllEmp(){
        List<Emp> empall=jdbcTemplate.query(get_emp_all,new EmpMapper());
        return empall;
    }

    @Override
    public List<Emp> findEmpDetails(String username) {
        List<Emp> empdetails=jdbcTemplate.query(get_emp_details,new EmpMapper(),username);
        return empdetails;
    }

    @Override
    public List<Emp> findAllTeams(String username) {
        String get_teams="select * from employee where team IN(select distinct team from employee where email=?)";
        List<Emp> empTeams=jdbcTemplate.query(get_teams,new EmpMapper(),username);
        return empTeams;
    }

    @Override
    public void save(Emp emp) {
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={emp.getEmployee_name(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getEmail(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications(),emp.getPassword()};
        String insert_query_skills="insert into user_skills(employee_name) values("+"'"+emp.getEmployee_name()+"'"+")";

        jdbcTemplate.update(insert_query,args,types);
        jdbcTemplate.update(insert_query_skills);
    }

    @Override
    public void delete(long e_id) {
        boolean b = jdbcTemplate.update(delete_skill_query, new Object[]{e_id}) > 0;
        boolean c= jdbcTemplate.update(delete_emp_query, new Object[]{e_id}) > 0;
    }

    @Override
    public int update(Emp emp, long e_id) {
        Object[] params = {emp.getEmployee_name(),emp.getEmail(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),e_id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.LONGVARCHAR};
        return jdbcTemplate.update(update_query,params,types);
    }

    @Override
    public HashMap<Integer, String> validate(String required_skill, int min_req_rating, int complexity) {
        String validate_query = "select e.e_id,e.employee_name, e.slack_time,u.p_skills,u.a_skills,u.a_self_rating,u.p_manager_rating" +
                " from employee as e,user_skills as u where e.e_id=u.e_id and (u.p_skills=? or u.a_skills=?) " +
                "and (u.a_self_rating>=? or u.p_manager_rating>=?) " +
                "and (e.slack_time>=?)";
        Object[] params = {required_skill,required_skill, min_req_rating,min_req_rating, complexity};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.INTEGER, Types.INTEGER};
        HashMap<Integer,String> hashMap=jdbcTemplate.query(validate_query, params, types, (ResultSet rs) -> {
            HashMap<Integer, String> hmap = new HashMap<>();
            while (rs.next()) {
                String details = rs.getInt("e_id") + "," + rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
//                String details = rs.getInt("e_id") + "," + rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
                hmap.put(rs.getInt("e_id"), details);
            }
            return hmap;
        });
        JSONObject json = new JSONObject(hashMap);
        System.out.println(json);
        //get one key
        Integer key = hashMap.entrySet().stream().findFirst().get().getKey();
        System.out.println("Key: " + key);

        //get one value
        String value = hashMap.entrySet().stream().findFirst().get().getValue();
        System.out.println("Value: " + value);
        return hashMap;

    }

}
