import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();


        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);


        //CREATE
        post("/departments/:departmentId/news/:newsId", "application/json", (req, res) -> {

            int departmentId = Integer.parseInt(req.params("departmentId"));
            int newsId = Integer.parseInt(req.params("newsId"));
            Department department = departmentDao.findById(departmentId);
            News news = newsDao.findById(newsId);

            if (department != null && news != null){
                newsDao.addNewsToDepartment(news, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and News '%s' have been associated",news.getPost(), department.getName()));
            }
            else {
                throw new ApiException(404, String.format("Restaurant or Foodtype does not exist"));
            }
        });

        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            else if (departmentDao.getAllNewsForADepartment(departmentId).size()==0){
                return "{\"message\":\"I'm sorry, but no news are listed for this department.\"}";
            }
            else {
                return gson.toJson(departmentDao.getAllNewsForADepartment(departmentId));
            }
        });

        get("/news/:id/departments", "application/json", (req, res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            News newsToFind = newsDao.findById(newsId);
            if (newsToFind == null){
                throw new ApiException(404, String.format("No news with the id: \"%s\" exists", req.params("id")));
            }
            else if (newsDao.getAllDepartmentsForANews(newsId).size()==0){
                return "{\"message\":\"I'm sorry, but no departments are listed for this news.\"}";
            }
            else {
                return gson.toJson(newsDao.getAllDepartmentsForANews(newsId));
            }
        });

        post("/departments/:departmentId/users/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            User user = gson.fromJson(req.body(), User.class);
            user.setDepartmentId(departmentId);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });


        //READ
        get("/departments", "application/json", (req, res) -> {
            System.out.println(departmentDao.getAll());

            if(departmentDao.getAll().size() > 0){
                return gson.toJson(departmentDao.getAll());
            }

            else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(departmentToFind);
        });


        post("/user/new", "application/json", ((request, response) -> {
            User user = gson.fromJson(request.body(),User.class);
            userDao.add(user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        }) );

        get("/users", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(userDao.getAll());//send it back to be displayed
        });

    }
}
