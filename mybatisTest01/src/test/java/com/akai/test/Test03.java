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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test03 {
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
    public void testSingleArg() {
        Emp o = sqlSession.selectOne("findById", 7521);
        System.out.println(o);
    }

    @Test
    public void testMapArg() {
        // 测试map集合作为参数
        // sqlSession.selectList("findByIdAndSal", 20, 2000);    出错，selectList方法只能接收一个参数
        Map<String, Object> map = new HashMap<>();
        map.put("deptno", 20);
        map.put("sal", 1500.0);
        List<Emp> list = sqlSession.selectList("findByIdAndSal", map);
        list.forEach(System.out::println);
    }

    @Test
    public void testEmpArg() {
        // 测试Emp对象作为参数
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setSal(1500.0);
        List<Emp> list = sqlSession.selectList("findByIdAndSal2", emp);
        list.forEach(System.out::println);
    }

    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}
