package com.auth.login.dao;
import com.auth.login.model.UserSkill;
import com.auth.login.rowmapper.UserSkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class UserSkillRepoImpl implements UserSkillRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String get_user_skill_query="SELECT e_id, employee_name, p_skills, p_proficiency_level, p_self_rating, p_manager_rating, p_rating_delta, a_skills," +
            " a_self_rating, a_proficiency_level,comments from user_skills where e_id=?";

    private static final String get_all_skills="SELECT * FROM user_skills";

    private static final String update_skill_query="UPDATE user_skills set p_skills=?, a_skills=?, aspired_skills=?, p_self_rating=?, a_self_rating=?, comments=?, p_manager_rating=? where e_id=?";

    private static final String insert_new_userSkill="INSERT INTO user_skills(p_skills,a_skills,aspired_skills,p_self_rating,a_self_rating,comments) values(?,?,?,?,?,?)";

    private static final String get_skill_details="select * from user_skills where e_id IN(select e_id from employee where email=?)";

    private static final String get_team_skills="select * from user_skills where e_id IN(select team from employee where email=?)";

    @Override
    public UserSkill getById(int e_id) {
        return jdbcTemplate.queryForObject(get_user_skill_query,
                new Object[]{e_id},new UserSkillMapper());
    }

    @Override
    public List<UserSkill> findAllSkills(){
        List<UserSkill> skills_all=jdbcTemplate.query(get_all_skills,new UserSkillMapper());;
        return skills_all;
    }

    @Override
    public List<UserSkill> findSkillDetails(String username) {
        List<UserSkill> skillDetail=jdbcTemplate.query(get_skill_details,new UserSkillMapper(),username);
        return skillDetail;
    }

    @Override
    public List<UserSkill> findAllTeamSkills(String username) {
        List<UserSkill> teamSkills=jdbcTemplate.query(get_team_skills,new UserSkillMapper(),username);
        return teamSkills;
    }

    @Override
    public boolean deleteUserSkill(long e_id) {
        String delete_query = "delete from user_skills where e_id = ?";
        return jdbcTemplate.update(delete_query, new Object[]{e_id}) > 0;
    }

    public void insertUserSkill(UserSkill userSkill){
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={userSkill.getP_skills(),userSkill.getA_skills(),userSkill.getAspired_skills(),userSkill.getP_self_rating(),userSkill.getA_self_rating(),userSkill.getComments()};
        jdbcTemplate.update(insert_new_userSkill,args,types);
    }

    @Override
    public int update(UserSkill userSkill, long e_id) {
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.LONGVARCHAR};
        Object[] args={userSkill.getP_skills(),userSkill.getA_skills(),userSkill.getAspired_skills(),userSkill.getP_self_rating(),userSkill.getA_self_rating(),userSkill.getComments(),userSkill.getP_manager_rating(),e_id};
        return jdbcTemplate.update(update_skill_query,args,types);
    }
}
