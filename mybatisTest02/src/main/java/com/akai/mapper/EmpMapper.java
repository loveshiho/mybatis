package com.akai.mapper;

import com.akai.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    List<Emp> findAll();

    Emp findById(int id);

    List<Emp> findByIdAndSal(@Param("deptno") int id, @Param("sal") double sal);  // 底层被封装为 map集合

    List<Emp> findByIdAndSal2(@Param("empa") Emp empa, @Param("empb") Emp empb);
}
