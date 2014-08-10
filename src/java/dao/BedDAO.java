/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Bed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BedDAO {

    public static List<Bed> retrieveAllBeds() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Bed> results = new ArrayList<Bed>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM Bed";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String bedId = rs.getString("bedID");
                String wardId = rs.getString("wardId");
                String availabilityStatus = rs.getString("availabilityStatus");
                Bed obj = new Bed(bedId, wardId, availabilityStatus);

                results.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public Bed retrieve(String bedId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bed bed = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Bed where BedID = ?");
            stmt.setString(1, bedId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                bed = new Bed(rs.getString(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return bed;
    }
}
