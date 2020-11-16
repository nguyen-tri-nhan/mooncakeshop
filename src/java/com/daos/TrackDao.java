/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.TrackDTO;
import com.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class TrackDao {

    private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

    public List<TrackDTO> loadTrack(String username) throws Exception {
        List<TrackDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT s.id, s.orderdate, a.fullname, a.phone, a.addr, t.name"
                    + " FROM tblOrders s, tblAddresses a, tblCashTypes t"
                    + " WHERE s.username = a.username"
                    + " AND s.addressid = a.id"
                    + " AND s.cashtype = t.id"
                    + " AND s.username = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            while (rs.next()) {                
                list.add(new TrackDTO(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getTimestamp(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
}
