package com.akai.mapper;

import com.akai.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DeptMapper {
    Dept findDeptByDeptno(int deptno);
    @Select("select * from db_01.dept where DEPTNO = #{id}")
    Dept findById(int id);
    @Update("update dept set dname = #{dname}, loc = #{loc} where deptno = #{deptno}")
    int updateDept(Dept dept);
    @Insert("insert into db_01.dept values(default, #{dname}, #{loc})")
    int addDept(Dept dept);
    @Delete("delete from dept where deptno = #{id}")
    int removeDept(int id);
}
