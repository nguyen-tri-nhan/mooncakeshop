/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.OrderDTO;
import com.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class OrderDAO {
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
    
    public boolean insertOrder(OrderDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrders(username, addressid, orderdate, cashtype, iscashed, isdelivered)"
                    + " VALUES(?,?,?,?,?,?)";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setInt(2, dto.getAddressid());
            stm.setTimestamp(3, dto.getOrderdate());
            stm.setInt(4, dto.getCashtype());
            stm.setBoolean(5, dto.isCashed());
            stm.setBoolean(6, dto.isDelivered());
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public void deleteOrder(int id) throws Exception{
        try {
            String sql = "DELETE tblOrders WHERE id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public int currentID() throws Exception{
        int id = 0;
        try {
            String sql = "SELECT MAX(id) FROM tblOrders";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return id;
    }
}
