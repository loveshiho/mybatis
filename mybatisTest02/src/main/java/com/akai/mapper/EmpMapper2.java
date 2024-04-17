package com.akai.mapper;

import com.akai.pojo.Emp;

import java.util.List;

public interface EmpMapper2 {
    List<Emp> findByCondition(Emp emp);
    List<Emp> findByCondition2(Emp emp);
}
