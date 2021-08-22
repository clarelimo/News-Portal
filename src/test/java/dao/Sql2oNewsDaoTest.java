package dao;

import models.Department;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News news = setupNews();
        assertEquals(1, news.getId());
    }

    @Test
    public void getAll() throws Exception {
        News news = setupNews();
        News news1 = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        News news = setupNews();
        News news1 = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(news.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News news = setupNews();
        News news1 = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void addNewsToDepartment() throws Exception{
        Department department = setupDepartment();
        Department department1 = setupDepartment();

        departmentDao.add(department);
        departmentDao.add(department1);

        News news = setupNews();
        newsDao.add(news);

        newsDao.addNewsToDepartment(news,department);
        newsDao.addNewsToDepartment(news,department1);

        assertEquals(2, newsDao.getAllDepartmentsForANews(news.getId()).size());
    }

    //helpers
    public News setupNews(){
        News news =  new News("BBI demolition");
        newsDao.add(news);
        return news;
    }


    public Department setupDepartment(){
        Department department =  new Department("technology","build tech",20);
        departmentDao.add(department);
        return department;
    }
}