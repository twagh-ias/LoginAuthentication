package com.auth.login.dao;

import com.auth.login.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmpRepoImpl implements EmpRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String get_emp_query="SELECT employee_name,email,department,org_level,team,total_exp," +
            "ad_tech_exp,slack_time,certifications from employee where e_id=?";

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
                    return e;
                });
    }
}
