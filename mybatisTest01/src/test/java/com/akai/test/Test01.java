package com.akai.test;

import com.akai.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test01 {
    @Test
    public void testFindAll(){
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
        SqlSession sqlSession = factory.openSession();
        // 调用 sql语句
        List<Dept> list = sqlSession.selectList("findAll"); // 根据 id找到 sql语句
        // 关闭 sqlSession
        sqlSession.close();
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }

}
