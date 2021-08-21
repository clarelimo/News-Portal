package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(position, that.position) &&
                Objects.equals(role, that.role) &&
                departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, role, id,departmentId);
    }
}
