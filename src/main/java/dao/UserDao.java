package dao;

import models.Department;
import models.User;

import java.util.List;

public interface UserDao {
    //create
    void add(User user);
    void addUserToDepartment(User user, Department department);

    //read
    List<User> getAll();
    List<User> getAllUsersForADepartment(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
