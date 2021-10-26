package com.auth.login.service;
import com.auth.login.model.UserSkill;

import java.util.List;

public interface UserSkillService {
    UserSkill getById(int e_id);

    List<UserSkill> findAllSkills();
}
