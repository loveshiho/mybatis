package com.akai.test;

import com.akai.mapper.EmpMapper;
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

public class Test03 {
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
    public void testAddEmp() {
        int i = empMapper.addEmp(new Emp(null, "TOM", "SALESMAN", 7521, new Date(), 2314.0, 100.0, 10));
        System.out.println(i);
    }

    @Test
    public void testUpdateEnameById() {
        int i = empMapper.updateEnameById(7938, "cao");
        System.out.println(i);
    }

    @Test
    public void testDeleteById() {
        int i = empMapper.deleteById(7938);
        System.out.println(i);
    }
    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.commit();
        sqlSession.close();
    }

}