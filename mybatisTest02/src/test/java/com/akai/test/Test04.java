package com.akai.test;

import com.akai.mapper.EmpMapper;
import com.akai.mapper.EmpMapper2;
import com.akai.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test04 {
    private SqlSession sqlSession;
    private EmpMapper2 empMapper;

    @Before
    public void init() {
        // ssfb --> 工人
        // factory --> 工厂
        // sqlSession --> 车，用于装 sql语句
        // sqlMapConfig --> 图纸
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");   // 找项目编译后的根路径，即 target包下的classes
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory factory = ssfb.build(resourceAsStream);
        sqlSession = factory.openSession();
        empMapper = sqlSession.getMapper(EmpMapper2.class);
    }

    @Test
    public void findByCondition() {
        Emp emp = new Emp();
        List<Emp> emps = empMapper.findByCondition(emp);
        emps.forEach(System.out::println);
    }
    @Test
    public void findByCondition2() {
        Emp emp = new Emp();
        emp.setDeptno(10);
        List<Emp> emps = empMapper.findByCondition(emp);
        emps.forEach(System.out::println);
    }
    @Test
    public void findByCondition3() {
        Emp emp = new Emp();
        emp.setEname("SMITH");
        List<Emp> emps = empMapper.findByCondition(emp);
        emps.forEach(System.out::println);
    }
    @Test
    public void findByCondition4() {
        Emp emp = new Emp();
        emp.setEname("FORD");
        emp.setEmpno(7369);
        List<Emp> emps = empMapper.findByCondition2(emp);
        emps.forEach(System.out::println);
    }
    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.commit();
        sqlSession.close();
    }

}