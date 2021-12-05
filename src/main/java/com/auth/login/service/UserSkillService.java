package com.auth.login.service;
import com.auth.login.model.UserSkill;

import java.util.List;

public interface UserSkillService {
    UserSkill getById(int e_id);

    List<UserSkill> findAllSkills();

    List<UserSkill> findSkillDetails(String username);

    List<UserSkill> findAllTeamSkills(String username);

    boolean deleteUserSkill(long id);

    void insertUserSkill(UserSkill userSkill);

    int update(UserSkill userSkill,long e_id);
}
