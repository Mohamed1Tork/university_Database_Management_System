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
public class ReportDAO {

    // method to retrieve a Report about a specific Course 
    public static ArrayList<ReportDTO> getReports(String n) {
        ArrayList<ReportDTO> report = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MO", "123")) {
            
            System.out.println("Connection to the database established.");

            try (PreparedStatement pst = con.prepareStatement(
                    "SELECT e.course_id, c.course_name,  semester, t.stu_name, e.grade,\n" +
                    "calc_avg_GPA((SELECT course_id FROM courses WHERE course_name = ?))\n" +
                    "FROM  enrollments e, students t, courses c \n" +
                    "WHERE  e.stu_id = t.stu_id \n" +
                    "AND e.course_id = c.course_id AND c.course_name = ? ")) {

                pst.setString(1, n);
                pst.setString(2, n);

                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    ReportDTO obj = new ReportDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6));
                    report.add(obj);
                }
            } catch (SQLException ex) {
                
                System.out.println("Failed to execute SQL query.");
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            
            System.out.println("Failed to establish connection to the database.");
            Logger.getLogger(DepartmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return report;
    }

}
