package com.auth.login.dao;


import com.auth.login.model.UserSkill;

import java.util.List;

public interface UserSkillRepo {
    UserSkill getById(int e_id);

    public List<UserSkill> findAllSkills();
}
