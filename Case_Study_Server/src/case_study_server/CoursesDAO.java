/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LORD LAPTOP
 */
public class CoursesDAO {
    
    // method to insert a Course to the Courses table 
    public static int insertCourse(String id, String n, String h, String d) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            
            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("INSERT INTO COURSES "
                    + "(COURSE_ID,COURSE_NAME,CREDIT_HOURS,DEPT_ID)"
                    + " VALUES (?, ?, ?,?)")) {
                PST.setInt(1, Integer.valueOf(id));
                PST.setString(2, n);
                PST.setInt(3, Integer.valueOf(h));
                PST.setInt(4, Integer.valueOf(d));
                result = PST.executeUpdate();

            } catch (SQLException ex) {
               
                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            
            System.out.println("Failed to establish connection to the database.");
            ex.printStackTrace();
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    // Method to check is the Course is already exist or not 
    public static String checkCourse(String id) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM COURSES WHERE COURSE_ID = ? ")) {
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
    public static String checkCourse_n(String n) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM COURSES WHERE COURSE_NAME = ? ")) {
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

    // method to update the Courses info depend on its id
    public static int updateCourse(String id, String n, String h, String d) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            
            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("UPDATE COURSES "
                    + "SET COURSE_NAME = ? ,CREDIT_HOURS = ? , DEPT_ID = ?"
                    + "WHERE COURSE_ID = ? ")) {
                PST.setInt(4, Integer.valueOf(id));
                PST.setString(1, n);
                PST.setString(2, h);
                PST.setInt(3, Integer.valueOf(d));
                result = PST.executeUpdate();

            } catch (SQLException ex) {

                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            
            System.out.println("Failed to establish connection to the database.");
            ex.printStackTrace();
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // method to delete a Course by its name
    public static int deleteCourse(String n) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            
            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("DELETE FROM COURSES "
                    + "WHERE COURSE_NAME = ? ")) {
                PST.setString(1, n);
                result = PST.executeUpdate();

            } catch (SQLException ex) {
                
                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            
            System.out.println("Failed to establish connection to the database.");
            ex.printStackTrace();
            Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // method to retrieve the all Courses information
    public static ArrayList<CoursesDTO> getCourses() {
        ArrayList<CoursesDTO> allCourses = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            
            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT * from COURSES")) {

                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    CoursesDTO obj = new CoursesDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4));
                    allCourses.add(obj);
                }
            } catch (SQLException ex) {
                
                System.out.println("Failed to establish connection to the database.");
                ex.printStackTrace();
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allCourses;
    }
    
    public static ArrayList<StudentsDTO> getStudentsByCourse(String courseName) {
        ArrayList<StudentsDTO> allStudents = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            System.out.println("Connection to the database established.");

            String query = "SELECT s.stu_id , s.stu_name ,g.grade \n" +
                            "FROM students s , enrollments g\n" +
                            " WHERE S.STU_ID = G.STU_ID\n" +
                            " and G.COURSE_ID = (select course_id from courses where course_name = ?)";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, courseName);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    StudentsDTO obj = new StudentsDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    allStudents.add(obj);
                }
            } catch (SQLException ex) {
                System.out.println("Failed to execute query: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to establish connection to the database.");
            ex.printStackTrace();
        }

        return allStudents;
    }
    
}
