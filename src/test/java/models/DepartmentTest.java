package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    @Test
    public void getNameReturnsCorrectName() {
        Department department = setupDepartment();
        assertEquals("technology", department.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() {
        Department department = setupDepartment();
        assertEquals("build tech", department.getDescription());
    }

    @Test
    public void getNumberOfEmployeesReturnsCorrectNumberOfEmployees() {
        Department department = setupDepartment();
        assertEquals(20, department.getNumberOfEmployees());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Department department =setupDepartment();
        department.setName("finance");
        assertNotEquals("technology",department.getName());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() throws Exception {
        Department department = setupDepartment();
        department.setDescription("all about money");
        assertNotEquals("build tech",department.getDescription());
    }

    @Test
    public void setNumberOfEmployeesSetsCorrectNumberOfEmployees() throws Exception {
        Department department = setupDepartment();
        department.setNumberOfEmployees(80);
        assertNotEquals(20,department.getNumberOfEmployees());
    }

    //helper
    public Department setupDepartment(){
        return  new Department("technology","build tech",20);
    }

}