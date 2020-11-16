/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.AddressDTO;
import com.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class AddressDAO {
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
    
    public boolean insertAddress(AddressDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "INSERT INTO tblAddresses(username,fullname,phone,addr)"
                    + " VALUES(?,?,?,?)";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getFullname());
            stm.setString(3, dto.getPhone());
            stm.setString(4, dto.getAddress());
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public int currentID() throws Exception{
        int id = 0;
        try {
            String sql = "SELECT MAX(id) FROM tblAddresses";
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
    
    public void deleteAddress(int id) throws Exception{
        try {
            String sql = "DELETE tblAddresses WHERE id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }
}
