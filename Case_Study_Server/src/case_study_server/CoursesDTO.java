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
public class CoursesDTO {
    private int cour_id;
    private String cour_name;
    private int credit_hours;
    private int dept_id;

    public CoursesDTO(int cour_id, String cour_name, int credit_hours, int dept_id) {
        this.cour_id = cour_id;
        this.cour_name = cour_name;
        this.credit_hours = credit_hours;
        this.dept_id = dept_id;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public String getCour_name() {
        return cour_name;
    }

    public void setCour_name(String cour_name) {
        this.cour_name = cour_name;
    }

    public int getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
    
    
}
