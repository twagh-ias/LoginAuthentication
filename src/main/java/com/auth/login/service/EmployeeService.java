package com.auth.login.service;

import com.auth.login.model.Emp;

import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    List<Emp> findAllEmp();

    void save(Emp emp);

    boolean delete(long id);

    int update(Emp emp,long e_id);
}
