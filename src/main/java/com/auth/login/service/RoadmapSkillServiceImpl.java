package com.auth.login.service;

import com.auth.login.dao.RoadmapSkillRepo;
import com.auth.login.model.Roadmap_skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapSkillServiceImpl implements RoadmapSkillService {

    @Autowired
    RoadmapSkillService roadmapSkillService;

    @Autowired
    RoadmapSkillRepo roadmapSkillRepo;

    public Roadmap_skills getById(int r_id){
        return roadmapSkillRepo.getById(r_id);
    }

    public List<Roadmap_skills> findAllDetails(){
        return roadmapSkillRepo.findAllDetails();
    }

    public void save(Roadmap_skills roadmap_skills){
        roadmapSkillRepo.save(roadmap_skills);
    }

    public void delete(long r_id){
        roadmapSkillRepo.delete(r_id);
    }

    public int update(Roadmap_skills roadmap_skills, long r_id){
        return roadmapSkillRepo.update(roadmap_skills,r_id);
    }
}
