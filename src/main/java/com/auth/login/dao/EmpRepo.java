package com.auth.login.dao;

import com.auth.login.model.Emp;

import java.util.List;

public interface EmpRepo {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();
}
