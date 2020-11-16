/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.UserDTO;
import com.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Lenovo
 */
public class UserDAO {

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

    public UserDTO checkLogin(String username, String password) throws Exception {
        UserDTO dto = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT username,firstname,lastname,registerdate,roleID"
                    + " FROM tblUsers"
                    + " WHERE isactive = ? AND roleID != ?"
                    + " AND username = ? AND password = ?";
            String hexed = new DigestUtils("SHA-256").digestAsHex(password);
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setInt(2, 1);
            stm.setString(3, username);
            stm.setString(4, hexed);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new UserDTO(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), "***", rs.getDate("registerdate"), rs.getInt("roleID"), true);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return dto;
    }
    //TODO: create user here
    public boolean isExistedUser(String email) {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT username from tblUsers WHERE username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {

        } finally {

        }
        return false;
    }
    public boolean createUser(UserDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                //INSERT INTO tblUsers(email,firstname,lastname,password,registerdate,roleID,statusID) VALUES ('admin','admin','nhan','1','09/16/2020',-1,-1)
                String sql = "INSERT INTO tblUsers "
                        + "VALUES(?,?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getFirstname());
                stm.setString(3, dto.getLastname());
                stm.setString(4, new DigestUtils("SHA-256").digestAsHex(dto.getPassword()));
                stm.setDate(5, dto.getRegisterdate());
                stm.setInt(6, dto.getRoleid());
                stm.setBoolean(7, dto.isActive());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            check = false;
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return check;
    }
}
