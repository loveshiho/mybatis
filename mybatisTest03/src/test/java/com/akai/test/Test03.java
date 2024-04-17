package com.akai.test;

import com.akai.mapper.DeptMapper;
import com.akai.mapper.EmpMapper;
import com.akai.mapper.ProjectMapper;
import com.akai.pojo.Dept;
import com.akai.pojo.Emp;
import com.akai.pojo.Project;
import com.akai.pojo.ProjectRecord;
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
    private ProjectMapper projectMapper;

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
        projectMapper = sqlSession.getMapper(ProjectMapper.class);
    }
    @Test
    public void testManyToMany() {
        Project project = projectMapper.findProjectJoinEmpsByPid(2);
        String pname = project.getPname();
        Integer money = project.getMoney();
        System.out.println(pname + " : " + money);
        List<ProjectRecord> list = project.getProjectRecords();
        list.forEach(System.out::println);
    }
    @After
    public void release() {
        // 关闭 sqlSession
        sqlSession.close();
    }

}