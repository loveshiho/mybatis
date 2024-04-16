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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test04 {
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
    public void testFindAll() {
        List<Emp> all = sqlSession.selectList("findAll");
        all.forEach(System.out::println);
    }

    // 手动提交事务
    // sqlSession.commit();
    /* 增删改 要提交事务
     * sqlSession.commit(); 手动提交事务
     * sqlSession = factory.openSession(true); 设置事务自动提交
     * */
    @Test
    public void TestAddEmp() {
        Emp emp = new Emp(null, "cao", "Java", 7839, new Date(), 3000.0, 200.0, 10);
        int rows = sqlSession.insert("addEmp", emp);
        System.out.println(rows);
        // DEBUG - Rolling back JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4d14b6c2]，说明事务回滚了
        // 手动提交
        sqlSession.commit();
        // DEBUG - Committing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4d14b6c2]
    }

    @Test
    public void testUpdate() {
        Emp emp = new Emp();
        emp.setEname("晓明");
        emp.setEmpno(7936);
        int rows = sqlSession.update("updateEmp", emp);
        System.out.println(rows);
        sqlSession.commit();
    }

    @Test
    public void testDeleteById() {
        int rows = sqlSession.delete("deleteById", 7936);
        System.out.println(rows);
        sqlSession.commit();
    }

    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}
