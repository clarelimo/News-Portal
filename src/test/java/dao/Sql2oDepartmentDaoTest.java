package dao;

import models.Department;
import models.News;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingDepartmentSetsId() throws Exception {
        Department department = setupDepartment();
        assertEquals(1, department.getId());
    }

    @Test
    public void getAll() throws Exception {
        Department department = setupDepartment();
        Department department1 = setupDepartment();
        assertEquals(2, departmentDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        assertEquals(2, departmentDao.getAll().size());
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }
    @Test
    public void getAllNewsForADepartmentReturnsNewsCorrectly() throws Exception {
        News news  = new News("The Cliff");
        newsDao.add(news);

        News otherNews  = new News("The rush hour");
        newsDao.add(otherNews);

        Department department = setupDepartment();
        departmentDao.add(department);
        departmentDao.addDepartmentToNews(department,news);
        departmentDao.addDepartmentToNews(department,otherNews);

        News[] testNews = {news, otherNews};
        List<News> test2News = departmentDao.getAllNewsForADepartment(department.getId());
        for (News n:test2News
        ) {

            System.out.println("from dao "+n.getPost());

        }
        assertEquals(2,departmentDao.getAllNewsForADepartment(department.getId()).size());

        assertEquals(Arrays.asList(testNews), departmentDao.getAllNewsForADepartment(department.getId()));
    }

    //helpers
    public Department setupDepartment(){
        Department department =  new Department("technology","build tech",20);
        departmentDao.add(department);
        return department;
    }

    public News setupNews(){
        News news =  new News("BBI demolition");
        newsDao.add(news);
        return news;
    }


}