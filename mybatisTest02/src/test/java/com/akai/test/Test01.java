package com.akai.test;

import com.akai.mapper.EmpMapper;
import com.akai.pojo.Dept;
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
import java.util.List;

public class Test01 {
    private SqlSession sqlSession;
    private EmpMapper empMapper;

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
        empMapper = sqlSession.getMapper(EmpMapper.class);
    }

    @Test
    public void testFindAll() {
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);    // 代理模式的体现
        List<Emp> all = empMapper.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        Emp emp = empMapper.findById(7900);
        System.out.println(emp);
    }

    @Test
    public void testFindByIdAndSal() {
        List<Emp> emps = empMapper.findByIdAndSal(20, 1000);
        emps.forEach(System.out::println);
    }

    @Test
    public void testFindByIdAndSal2() {
        Emp empa = new Emp();
        empa.setDeptno(20);
        Emp empb = new Emp();
        empb.setSal(1000.0);
        List<Emp> emps = empMapper.findByIdAndSal2(empa, empb);
        emps.forEach(System.out::println);
    }

    @Test
    public void testFindByEname() {
        List<Emp> emps = empMapper.findByEname("a");
        emps.forEach(System.out::println);
    }
    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}
