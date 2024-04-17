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

public class Test02 {
    private SqlSession sqlSession;
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
        deptMapper = sqlSession.getMapper(DeptMapper.class);
    }
    @Test
    public void testFindDeptJoinEmpsByDeptno() {
        Dept dept = deptMapper.findDeptJoinEmpsByDeptno(20);
        List<Emp> emps = dept.getEmps();
        emps.forEach(System.out::println);
    }
    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}