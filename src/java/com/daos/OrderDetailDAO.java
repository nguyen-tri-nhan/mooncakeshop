/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.OrderDetailDTO;
import com.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class OrderDetailDAO {
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
    
    public boolean insertOrderDetails(List<OrderDetailDTO> details) throws Exception{
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrderDetails(orderID, cakeID, quantity)"
                    + " VALUES (?,?,?)";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (OrderDetailDTO detail : details) {
                stm.setInt(1, detail.getOrderid());
                stm.setInt(2, detail.getCakeid());
                stm.setInt(3, detail.getQuantity());
                stm.executeUpdate();
            }
            conn.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally{
            closeConnection();
        }
        return check;
    }
}
