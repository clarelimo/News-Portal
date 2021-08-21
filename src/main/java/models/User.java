package models;

public class User {
    private String position;
    private String role;
    private int departmentId;
    private String name;
    private int id;

    public  User(String name,String position, String role, int departmentId){
        this.name = name;
        this.position = position;
        this.role = role;
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
