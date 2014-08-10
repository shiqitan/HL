/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;
import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author weiyi.ngow.2012
 */
public class AdminDAO {
    public Admin retrieve(String userid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from ADMIN where AdminID = ?");
            stmt.setString(1, userid);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                admin = new Admin(rs.getString(1), rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        
        return admin;
    }
}
