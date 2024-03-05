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
public class ReportDTO {
    private int cour_id;
    private String cour_Name;
    private String semester;
    private String stu_name;
    private String stu_grade;
    private Float avg_GPA;

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public String getCour_Name() {
        return cour_Name;
    }

    public void setCour_Name(String cour_Name) {
        this.cour_Name = cour_Name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public Float getAvg_GPA() {
        return avg_GPA;
    }

    public void setAvg_GPA(Float avg_GPA) {
        this.avg_GPA = avg_GPA;
    }

    public ReportDTO(int cour_id, String cour_Name, String semester, String stu_name, String stu_grade, Float avg_GPA) {
        this.cour_id = cour_id;
        this.cour_Name = cour_Name;
        this.semester = semester;
        this.stu_name = stu_name;
        this.stu_grade = stu_grade;
        this.avg_GPA = avg_GPA;
    }
    
    
}
