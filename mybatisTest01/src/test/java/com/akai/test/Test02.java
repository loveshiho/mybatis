package com.akai.test;

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
import java.util.Map;
import java.util.Set;

public class Test02 {
    private SqlSession sqlSession;

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
    }

    @Test
    public void testFindOne() {
        // 查询单个对象
        Emp emp = sqlSession.selectOne("findOne");
        System.out.println(emp);
    }

    @Test
    public void testFindAll() {
        // 查询多个对象集合
        List<Emp> list = sqlSession.selectList("EmpMapper.findAll");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindMap() {
        // 查询多个对象的Map集合
        Map<Integer, Emp> empMap = sqlSession.selectMap("findEmpMap", "EMPNO");
        Set<Integer> empnos = empMap.keySet();
        for (Integer empno : empnos) {
            System.out.println(empno + " : " + empMap.get(empno));
        }
    }

    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}
