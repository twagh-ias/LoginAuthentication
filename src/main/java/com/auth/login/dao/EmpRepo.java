package com.auth.login.dao;

import com.auth.login.model.Emp;
import com.auth.login.vo.UserVo;

import java.util.HashMap;
import java.util.List;

public interface EmpRepo {
    Emp getById(int e_id);

    List<Emp> findAllEmp();

    List<Emp> findEmpDetails(String username);

    void save(Emp emp);

    void delete(long id);

    int update(Emp emp, long e_id);

    HashMap<Integer, String> validate(String required_skill, int min_req_rating, int complexity);

    List<Emp> findAllTeams(String username);
}
