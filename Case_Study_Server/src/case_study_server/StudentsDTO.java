
package case_study_server;


public class StudentsDTO {
    private int stu_id;

   
    private String st_Name;
    private String city;
    private String street;
    private String faculty;
    private Float com_GPA;
    private int dept_id;
    private String grade;

    
    

    public StudentsDTO(int id , String n , String c , String s, String f, int d) {
        stu_id =id;
        st_Name = n;
        city = c;
        street = s;
        faculty = f ;
        dept_id = d;
        
    }
    public StudentsDTO(int id , String n ,String g) {
        stu_id =id;
        st_Name = n;
        grade = g ;
        
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getSt_Name() {
        return st_Name;
    }

    public void setSt_Name(String st_Name) {
        this.st_Name = st_Name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Float getCom_GPA() {
        return com_GPA;
    }

    public void setCom_GPA(Float com_GPA) {
        this.com_GPA = com_GPA;
    }
    
    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
    
     public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
    
}
