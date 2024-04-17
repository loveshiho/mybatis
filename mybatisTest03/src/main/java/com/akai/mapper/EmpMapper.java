package com.akai.mapper;

import com.akai.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    List<Emp> findAll();
}
