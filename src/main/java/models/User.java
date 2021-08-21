package models;

public class User {
    private String position;
    private String role;
    private String department;
    private String name;

    public  User(String name,String position, String role, String department){
        this.name = name;
        this.position = position;
        this.role = role;
        this.department = department;
    }
}
