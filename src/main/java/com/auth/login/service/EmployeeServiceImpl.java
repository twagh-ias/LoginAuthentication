package com.auth.login.service;

import com.auth.login.dao.EmpRepo;
import com.auth.login.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpRepo empRepo;

    @Override
    public Emp getById(int e_id)
    {
        return empRepo.getById(e_id);
    }

    @Override
    public List<Emp> findAllEmp(){
        return empRepo.findAllEmp();
    }

    @Override
    public void save(Emp emp) {
        empRepo.save(emp);
    }

    @Override
    public void delete(long id){
        empRepo.delete(id);
    }

    @Override
    public int update(Emp emp,long e_id){
        return empRepo.update(emp, e_id);
    }
}
