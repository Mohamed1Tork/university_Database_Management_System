/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

/**
 *
 * @author LORD LAPTOP
 */
public class DepartmentsDTO {
    private int dept_id;
    private String dept_Name;
    private String add_grade;

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_Name() {
        return dept_Name;
    }

    public void setDept_Name(String dept_Name) {
        this.dept_Name = dept_Name;
    }

    public String getAdd_grade() {
        return add_grade;
    }

    public void setAdd_grade(String add_grade) {
        this.add_grade = add_grade;
    }

    public DepartmentsDTO(int dept_id, String dept_Name, String add_grade) {
        this.dept_id = dept_id;
        this.dept_Name = dept_Name;
        this.add_grade = add_grade;
    }
    
}
