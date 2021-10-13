package com.auth.login.dao;

import com.auth.login.model.Emp;
import com.auth.login.model.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserSkillRepoImpl implements UserSkillRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String get_user_skill_query="SELECT p_skills, p_proficiency_level, a_skills, a_proficiency_level" +
            " from user_skills where e_id=?";

    @Override
    public UserSkill getById(int e_id) {
        return jdbcTemplate.queryForObject(get_user_skill_query,
                new Object[]{e_id},
                (rs, rowNum) -> {
                    UserSkill u = new UserSkill();
                    u.setP_skills(rs.getString("p_skills"));
                    u.setP_proficiency_level(rs.getString("p_proficiency_level"));
                    u.setA_skills(rs.getString("a_skills"));
                    u.setA_proficiency_level(rs.getString("a_proficiency_level"));

                    return u;
                });
    }
}
