package com.auth.login.service;

import com.auth.login.dao.UserSkillRepo;
import com.auth.login.model.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillServiceImpl implements UserSkillService{

    @Autowired
    UserSkillService userSkillService;

    @Autowired
    UserSkillRepo userSkillRepo;

    @Override
    public UserSkill getById(int e_id)
    {
        return userSkillRepo.getById(e_id);
    }

    @Override
    public List<UserSkill> findAllSkills(){
        return userSkillRepo.findAllSkills();
    }

    public List<UserSkill> findSkillDetails(String username){
        return userSkillRepo.findSkillDetails(username);
    }

    @Override
    public List<UserSkill> findAllTeamSkills(String username) {
        return userSkillRepo.findAllTeamSkills(username);
    }

    @Override
    public boolean deleteUserSkill(long id) {
        return userSkillRepo.deleteUserSkill(id);
    }

    public void insertUserSkill(UserSkill userSkill){
        userSkillRepo.insertUserSkill(userSkill);
    }

    public int update(UserSkill userSkill,long e_id){
        return userSkillRepo.update(userSkill,e_id);
    }
}
