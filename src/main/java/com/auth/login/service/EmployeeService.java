package com.auth.login.service;

import com.auth.login.model.Emp;

import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();
}
