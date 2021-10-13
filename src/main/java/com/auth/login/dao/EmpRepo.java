package com.auth.login.dao;

import com.auth.login.model.Emp;

public interface EmpRepo {
    Emp getById(int e_id);
}
