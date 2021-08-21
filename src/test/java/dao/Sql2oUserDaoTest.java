package dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Connection conn;
    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void addingUserSetsId() throws Exception {
        User user = setupUser();
        assertEquals(1, user.getId());
    }

    @Test
    public void getAll() throws Exception {
        User user = setupUser();
        User user2 = setupUser();
        assertEquals(2, userDao.getAll().size());
    }

//    @Test
//    public void getAllUsersForADepartment() throws Exception {
//        Department department1 = setupDepartment();
//        Department department2 = setupDepartment();
//        User user1 = setupUserForADepartment(department1);
//        User user2 = setupUserForADepartment(department1);
//        User otherUser = setupUserForADepartment(department2);
//
//        assertEquals(2, userDao.getAllUsersForADepartment(department1.getId()).size());
//    }

    @Test
    public void deleteById() throws Exception {
        User testUser = setupUser();
        User otherUser = setupUser();
        assertEquals(2, userDao.getAll().size());
        userDao.deleteById(testUser.getId());
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        User testUser = setupUser();
        User otherUser = setupUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    //helpers
    public User setupUser(){
        User user = new User("larry","manager","news anchor",1);
        userDao.add(user);
        return  user;
    }

    public User  setupUserForADepartment(Department department){
        User user = new User("larry","manager","news anchor",department.getId());
        userDao.add(user);
        return user;
    }

    public Department setupDepartment(){
        Department department =  new Department("technology","build tech",20);
        departmentDao.add(department);
        return department;
    }
}