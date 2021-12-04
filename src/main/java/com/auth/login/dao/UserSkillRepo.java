package com.auth.login.dao;


import com.auth.login.model.UserSkill;

import java.util.List;

public interface UserSkillRepo {
    UserSkill getById(int e_id);

    List<UserSkill> findAllSkills();

    List<UserSkill> findSkillDetails(String username);

    boolean deleteUserSkill(long id);

    void insertUserSkill(UserSkill userSkill);

    int update(UserSkill userSkill,long e_id);
}
