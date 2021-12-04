package com.auth.login.service;

import com.auth.login.model.Emp;
import com.auth.login.vo.UserVo;

import java.util.HashMap;
import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    List<Emp> findAllEmp();

    List<Emp> findAllTeams(String username);

    List<Emp> findEmpDetails(String username);

    void save(Emp emp);

    void delete(long id);

    int update(Emp emp,long e_id);

    HashMap<Integer, String> validate(String required_skill, int min_req_rating, int complexity);
}
