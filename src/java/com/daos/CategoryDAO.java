/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.CategoryDTO;
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
public class CategoryDAO {
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
    public List<CategoryDTO> getCategory() throws Exception{
        List<CategoryDTO> result = new ArrayList<>();
        try {
           String sql = "SELECT id, categoryname FROM tblCategories"; 
           conn = DBUtil.getConnection();
           stm = conn.prepareStatement(sql);
           rs = stm.executeQuery();
            while (rs.next()) {                
                result.add(new CategoryDTO(rs.getInt("id"),
                        rs.getString("categoryname")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
