package com.auth.login.dao;

import com.auth.login.model.Emp;
import com.auth.login.model.Roadmap_skills;

import java.util.List;

public interface RoadmapSkillRepo {

    Roadmap_skills getById(int r_id);

    List<Roadmap_skills> findAllDetails();

    void save(Roadmap_skills roadmap_skills);

    void delete(long r_id);

    int update(Roadmap_skills roadmap_skills, long r_id);
}
