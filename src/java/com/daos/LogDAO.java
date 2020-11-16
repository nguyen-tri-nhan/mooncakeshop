/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.LogDTO;
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
public class LogDAO {
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
    public void insertLog(LogDTO dto) throws Exception{
        try {
            String sql = "INSERT INTO tblLogs(username, act, descripton, actionDate)"
                    + " VALUES(?,?,?,?)";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getAct());
            stm.setString(3, dto.getDescription());
            stm.setTimestamp(4, dto.getActionDate());
            stm.executeUpdate();
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
    }
    
    public List<LogDTO> loadLog() throws Exception{
        List<LogDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT id, username, act, descripton, actionDate"
                    + " FROM tblLogs";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {                
                list.add(new LogDTO(rs.getInt("id"), rs.getString("username"),
                        rs.getString("act"), rs.getString("descripton"),
                        rs.getTimestamp("actionDate")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
}
