/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.TrackDetailDTO;
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
public class TrackDetailDAO {

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

    public List<TrackDetailDTO> loadDetail(int id) throws Exception {
        List<TrackDetailDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT c.name, d.quantity"
                    + " FROM tblCakes c, tblOrders o, tblOrderDetails d"
                    + " WHERE o.id = d.orderID"
                    + " AND c.id = d.cakeID"
                    + " AND o.id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {                
                list.add(new TrackDetailDTO(rs.getString(1), rs.getInt(2)));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
}
