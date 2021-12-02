package com.auth.login.dao;

import com.auth.login.model.Roadmap_skills;
import com.auth.login.rowmapper.RoadmapSkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class RoadmapSkillRepoImpl implements RoadmapSkillRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String get_roadmapSkills_query="select required_skill,min_req_skill_rating,complexity from roadmap_skills where r_id=?";

    private static final String get_all="select * from roadmap_skills";

    private static final String insert_query="insert into roadmap_skills(required_skill,min_req_skill_rating,complexity) values(?,?,?)";

    private static final String update_query="update roadmap_skills set required_skill = ?, min_req_skill_rating = ?, complexity=?";

    private static final String delete_skill_query="delete from roadmap_skills where r_id=?";

//    private static final String ;


    public Roadmap_skills getById(int r_id){
        return jdbcTemplate.queryForObject(get_roadmapSkills_query,
                new Object[]{r_id},
                (rs, rowNum) -> {
                    Roadmap_skills r=new Roadmap_skills();
                    r.setRequired_skills(rs.getString("required_skill"));
                    r.setMin_req_skill_rating(rs.getString("min_req_skill_rating"));
                    r.setComplexity(rs.getString("complexity"));
                    return r;
                });
    }

    public List<Roadmap_skills> findAllDetails(){
          List<Roadmap_skills> roadmapSkillAll=jdbcTemplate.query(get_all,new RoadmapSkillMapper());;
          return roadmapSkillAll;
    }

    public void save(Roadmap_skills roadmap_skills){
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={roadmap_skills.getRequired_skills(),roadmap_skills.getMin_req_skill_rating(),roadmap_skills.getComplexity()};
        jdbcTemplate.update(insert_query,args,types);
    }

    public void delete(long r_id){
      boolean b = jdbcTemplate.update(delete_skill_query, new Object[]{r_id}) > 0;
    }

    public int update(Roadmap_skills roadmap_skills, long r_id){
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={roadmap_skills.getRequired_skills(),roadmap_skills.getMin_req_skill_rating(),roadmap_skills.getComplexity()};
        return jdbcTemplate.update(update_query,args,types);
    }


}
