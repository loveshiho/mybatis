package com.akai.mapper;

import com.akai.pojo.Dept;

public interface DeptMapper {
    Dept findDeptByDeptno(int deptno);
}
