package com.akai.test;

import com.akai.mapper.DeptMapper;
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

public class Test03 {
    private SqlSession sqlSession;
    private EmpMapper empMapper;
    private DeptMapper deptMapper;
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
        deptMapper = sqlSession.getMapper(DeptMapper.class);
    }
    @Test
    public void testFindById() {
        Emp emp1 = empMapper.findEmpById(7499);
        Emp emp2 = empMapper.findEmpById(7499);
        System.out.println(emp1 == emp2);   // Total: 1
    }
    @After
    public void release() {
        sqlSession.commit();
        // 关闭 sqlSession
        sqlSession.close();
    }

}