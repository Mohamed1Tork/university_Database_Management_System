package case_study_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentsDAO {

    // method to insert a student to the students table 
    public static int insertStudent(String id, String n, String c, String s, String f, String d, String num) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst1 = con.prepareStatement("INSERT INTO STUDENTS "
                    + "(STU_ID,STU_NAME,CITY,STREET,FACULTY,DEPT_ID,COM_GPA)"
                    + " VALUES (?, ?, ?, ?, ?, ?, 0)");
                    PreparedStatement pst2 = con.prepareStatement("INSERT INTO STUDENT_CONTACT VALUES (?,?)")) {
                pst1.setInt(1, Integer.valueOf(id));
                pst1.setString(2, n);
                pst1.setString(3, c);
                pst1.setString(4, s);
                pst1.setString(5, f);
                pst1.setInt(6, Integer.valueOf(d));
                pst2.setInt(1, Integer.valueOf(id));
                pst2.setString(2, num);
                result = pst1.executeUpdate();
                pst2.executeUpdate();
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    // Method to check is the student is already exist or not 
    public static String checkStudent(String id) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM STUDENTS WHERE STU_ID = ? ")) {
                pst.setInt(1, Integer.valueOf(id));
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getString(1);
                }
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    // Method to check is the student is already exist or not by name
    public static String checkStudent_n(String n) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM STUDENTS WHERE STU_NAME = ? ")) {
                pst.setString(1, n);
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getString(1);
                }
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // method to update student data in the students table 
    public static int updateStudent(String id, String n, String c, String s, String f, String d) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("update STUDENTS "
                    + "set STU_NAME=?,CITY=?,STREET=?,FACULTY=?,DEPT_ID=?"
                    + "where STU_ID=?")) {

                pst.setString(1, n);
                pst.setString(2, c);
                pst.setString(3, s);
                pst.setString(4, f);
                pst.setInt(5, Integer.valueOf(d));
                pst.setInt(6, Integer.valueOf(id));

                result = pst.executeUpdate();
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Method to delete a student by id from students table 
    // cascading to this delete all students records in any table
    public static int deleteStudent(String n) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("delete from  STUDENTS where STU_NAME=?");
                 PreparedStatement pst2 = con.prepareStatement("delete from  ENROLLMENTS"
                         + " where STU_ID= (SELECT STU_ID FROM STUDENTS WHERE STU_NAME =?)");
                 PreparedStatement pst4 = con.prepareStatement("delete from  STUDENT_CONTACT"
                         + " where STU_ID= (SELECT STU_ID FROM STUDENTS WHERE STU_NAME =?)")) {
                pst.setString(1, n);
                pst2.setString(1, n);
                pst4.setString(1, n);
                
                pst2.executeUpdate();
                pst4.executeUpdate();
                result = pst.executeUpdate();
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // method to retrieve the student info with  his GPA 
    public static String getStudentGPA(String n) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'The Student ' || stu_name || ' is from ' || city || "
                    + "' in the Faculty of ' || faculty || ' has ' || TO_CHAR(com_GPA) || ' Comulative GPA.'\n "
                    + "FROM students\n"
                    + "WHERE stu_id = ?")) {
                pst.setInt(1, Integer.valueOf(n));

                try (ResultSet resultSet = pst.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getString(1);
                    }
                } catch (SQLException ex) {

                    System.out.println("Failed to process ResultSet.");
                    Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // method to add a grade for a dtudent in a specific course
    public static int addStudentGrade(String id, String c, String g,String s) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("INSERT INTO enrollments (grade, stu_id,semester,course_id)"
                    + "SELECT ?, ?,?, COURSE_ID FROM COURSES WHERE COURSE_NAME = ?")) {
                pst.setString(1, g);
                pst.setInt(2, Integer.valueOf(id));
                pst.setString(3, s);
                pst.setString(4, c);
                result = pst.executeUpdate();
                System.out.println(result);
            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
