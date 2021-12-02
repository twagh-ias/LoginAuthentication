package com.auth.login.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.auth.login.model.Roadmap_skills;
import org.springframework.jdbc.core.RowMapper;

public class RoadmapSkillMapper implements RowMapper<Roadmap_skills> {

    @Override
    public Roadmap_skills mapRow(ResultSet rs, int rowNum) throws SQLException {
        Roadmap_skills r=new Roadmap_skills();
        r.setRequired_skills(rs.getString("required_skill"));
        r.setMin_req_skill_rating(rs.getString("min_req_skill_rating"));
        r.setComplexity(rs.getString("complexity"));
        return r;
    }
}
