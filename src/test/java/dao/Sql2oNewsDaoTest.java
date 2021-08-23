package dao;

import models.Department;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "climo");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll();
        newsDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
//
//    @Test
//    public void addingNewsSetsId() throws Exception {
//        News news = setupNews();
//        assertEquals(1, news.getId());
//    }

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