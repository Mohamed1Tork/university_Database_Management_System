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
public class DepartmentsDAO {

    // method to insert a Department to the Departments table 
    public static int insertDepartment(String id, String n, String g) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("INSERT INTO DEPARTMENTS "
                    + "(DEPT_ID,DEPT_NAME,ADD_GRADE)"
                    + " VALUES (?, ?, ?)")) {
                PST.setInt(1, Integer.valueOf(id));
                PST.setString(2, n);
                PST.setString(3, g);
                result = PST.executeUpdate();

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

    // Method to check is the Departments is already exist or not 
    public static String checkDepartment(String id) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM DEPARTMENTS WHERE DEPT_ID = ? ")) {
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

    // Method to check is the Department is already exist or not by name
    public static String checkDepartment_n(String n) {
        String result = null;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT 'exist' FROM DEPARTMENTS WHERE DEPT_NAME = ? ")) {
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

    // method to update the departments info depend on its id
    public static int updateDepartment(String id, String n, String g) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("UPDATE DEPARTMENTS "
                    + "SET DEPT_NAME = ? ,ADD_GRADE = ?"
                    + "WHERE DEPT_ID = ? ")) {
                PST.setInt(3, Integer.valueOf(id));
                PST.setString(1, n);
                PST.setString(2, g);
                result = PST.executeUpdate();

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

    // method to delete a department by its name
    public static int deleteDepartment(String n) {
        int result = 0;

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement PST = con.prepareStatement("DELETE FROM DEPARTMENTS "
                    + "WHERE DEPT_NAME = ? ")) {
                PST.setString(1, n);
                result = PST.executeUpdate();

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

    // method to retrieve all Departments  info 
    public static ArrayList<DepartmentsDTO> getDepartments() {
        ArrayList<DepartmentsDTO> allDepartments = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {

            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement("SELECT * from DEPARTMENTS")) {

                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    DepartmentsDTO obj = new DepartmentsDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    allDepartments.add(obj);
                }
            } catch (SQLException ex) {

                System.out.println("Failed to establish connection to the database.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allDepartments;
    }

    public static ArrayList<CoursesDTO> getCoursesByDepartment(String departmentName) {
        ArrayList<CoursesDTO> allCourses = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            System.out.println("Connection to the database established.");

            String query = "SELECT * FROM Courses WHERE dept_id = (SELECT dept_id FROM departments WHERE dept_name = ?)";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, departmentName);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    CoursesDTO obj = new CoursesDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
                    allCourses.add(obj);
                }
            } catch (SQLException ex) {
                System.out.println("Failed to execute query: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to establish connection to the database.");
            ex.printStackTrace();
        }

        return allCourses;
    }

}
