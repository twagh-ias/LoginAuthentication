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
        return jdbcTemplate.queryForObject(sql1, new Object[] { username }, new UserRowMapper());
    }

    public List<Role> getRoles(String username) {
        List<Map<String, Object>> results = jdbcTemplate
                .queryForList("select user_role from user_role where user_name = ?", new Object[] { username });
        List<Role> roles = results.stream().map(m -> {
            Role role = new Role();
            role.setRole(String.valueOf(m.get("user_role")));
            return role;
        }).collect(Collectors.toList());
        return roles;
    }

    public void saveUser(UserRole user) {
        jdbcTemplate.update("insert into login(user_name, user_pass) values(?, ?)",
                new Object[] { user.getUsername(), user.getUserpwd() });
        user.getRoles().forEach(r -> jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into user_role(user_name, user_role) values(?, ?)",
                        new String[] { "user_name", "user_role" });
                ps.setString(1, user.getUsername());
                ps.setString(2, r);
                return ps;
            }
        }));
    }

}