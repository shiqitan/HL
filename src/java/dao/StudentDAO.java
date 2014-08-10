/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author weiyi.ngow.2012
 */
public class StudentDAO {
        public Student retrieve(String userid){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student student = null; 
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Student where StudentID = ?");
            stmt.setString(1, userid);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
               student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return student;
    }
}
