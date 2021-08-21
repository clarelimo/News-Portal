package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);
    void addNewsToDepartment(News news, Department department);

    //read
    List<News> getAll();

    //delete
    void deleteById(int id);
    void clearAll();
}
