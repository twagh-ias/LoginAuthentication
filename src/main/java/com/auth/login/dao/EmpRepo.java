package com.auth.login.dao;

import com.auth.login.model.Emp;

import java.util.List;

public interface EmpRepo {
    Emp getById(int e_id);

    List<Emp> findAllEmp();

    void save(Emp emp);

    void delete(long id);

    int update(Emp emp, long e_id);
}
