package com.auth.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.auth.login.model.Role;
import com.auth.login.model.User;
import com.auth.login.model.UserRole;
import com.auth.login.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    String sql1="select user_name, user_pass from login where user_name = ?";

    public User getUser(String username) {
        //int[] types = {Types.VARCHAR};
        //User user=jdbcTemplate.queryForObject(sql1, new Object[] { username }, new UserRowMapper());
        List<User> user= jdbcTemplate.query(sql1,new Object[]{username},new UserRowMapper());
        // return jdbcTemplate.queryForObject(sql1, new Object[] { username }, new UserRowMapper());
        if(user.isEmpty()){
            return null;
        }
        else {
            return user.get(0);
        }
    }

    public List<Role> getRoles(String username) {
        String sql2="select user_role from user_role where user_name = ?";
        List<Map<String, Object>> results = jdbcTemplate
                .queryForList(sql2, new Object[] { username });
        List<Role> roles = results.stream().map(m -> {
            Role role = new Role();
            role.setRole(String.valueOf(m.get("user_role")));
            return role;
        }).collect(Collectors.toList());
        return roles;
    }

//    login(e_id) should go to user_role table.
//    Value of e_id mapped to user_name in both tables

    public void saveUser(UserRole user) {
//        long logine_id = 0;
        String sql3="insert into login(user_name, user_pass) values(?, ?)";
        String sql4="insert into user_role(user_name, user_role) values(?, ?)";

//        String sql5="select e_id from login where user_name="+user.getUsername();
//        String sql6="insert into user_role(e_id) values("+logine_id+")";

        jdbcTemplate.update(sql3, new Object[] { user.getUsername(), user.getUserpwd() });
        user.getRoles().forEach(r -> jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql4, new String[] { "user_name", "user_role" });
                ps.setString(1, user.getUsername());
                ps.setString(2, r);
                return ps;
            }
        }));

//        User query = new User();
//        query=jdbcTemplate.queryForObject(sql5, new Object[]);
//        jdbcTemplate.update(sql3, new Object[] { user.getUsername(), user.getUserpwd() });
    }
    public void saveE_id(){

    }

}