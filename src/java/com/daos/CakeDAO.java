/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.dtos.CakeDTO;
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
public class CakeDAO {

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

    public List<CakeDTO> getAllCake(int page) throws Exception {
        List<CakeDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT id,name,descripton,categoryid,createdate,expirationdate,img,price,quantity,isVisible"
                    + " FROM tblCakes"
                    + " WHERE isVisible = ?"
                    + " AND quantity > 0"
                    + " ORDER BY createdate DESC"
                    + " OFFSET (? - 1)* 5 ROWS"
                    + " FETCH NEXT 5 ROWS ONLY";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setInt(2, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CakeDTO(rs.getInt("id"), rs.getString("name"),
                        rs.getString("descripton"), rs.getString("img"),
                        rs.getDate("createdate"), rs.getDate("expirationdate"),
                        rs.getDouble("price"), rs.getInt("categoryid"),
                        rs.getInt("quantity"), true));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<CakeDTO> getAll(int page) throws Exception {
        List<CakeDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT id,name,descripton,categoryid,createdate,expirationdate,img,price,quantity,isVisible"
                    + " FROM tblCakes"
                    + " ORDER BY createdate DESC"
                    + " OFFSET (? - 1)* 5 ROWS"
                    + " FETCH NEXT 5 ROWS ONLY";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CakeDTO(rs.getInt("id"), rs.getString("name"),
                        rs.getString("descripton"), rs.getString("img"),
                        rs.getDate("createdate"), rs.getDate("expirationdate"),
                        rs.getDouble("price"), rs.getInt("categoryid"),
                        rs.getInt("quantity"), rs.getBoolean("isVisible")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public CakeDTO getCake(int id, int quantity) throws Exception {
        CakeDTO cake = null;
        try {
            String sql = "SELECT id, name, price FROM tblCakes"
                    + " WHERE id = ? and quantity > ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, 0);
            rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                cake = new CakeDTO(id, name, null, null, null, null, price, 0, quantity, true);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return cake;
    }

    public int getCakeQuantity(int id) throws Exception {
        int quantity = -1;
        try {
            String sql = "SELECT quantity FROM tblCakes"
                    + " WHERE id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return quantity;
    }

    public boolean updateCake(CakeDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE tblCakes"
                    + " SET name = ?,"
                    + "descripton = ?, categoryid = ?, createdate = ?,"
                    + "expirationdate = ?,img = ?,price = ?,"
                    + "quantity = ?,isVisible = ?"
                    + " WHERE id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getDescripton());
            stm.setInt(3, dto.getCategoryID());
            stm.setDate(4, dto.getCreateDate());
            stm.setDate(5, dto.getExpirationDate());
            stm.setString(6, dto.getImg());
            stm.setDouble(7, dto.getPrice());
            stm.setInt(8, dto.getQuantity());
            stm.setBoolean(9, dto.isVisible());
            stm.setInt(10, dto.getId());
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLastID() throws Exception {
        int id = -1;
        try {
            String sql = "SELECT MAX(id) FROM tblCakes";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return id;
    }

    public boolean createCake(CakeDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblCakes(name,descripton,categoryid,"
                    + "createdate,expirationdate,img,price,quantity,isVisible)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getDescripton());
            stm.setInt(3, dto.getCategoryID());
            stm.setDate(4, dto.getCreateDate());
            stm.setDate(5, dto.getExpirationDate());
            stm.setString(6, dto.getImg());
            stm.setDouble(7, dto.getPrice());
            stm.setInt(8, dto.getQuantity());
            stm.setBoolean(9, dto.isVisible());
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public int countCake() throws Exception {
        int count = 0;
        try {
            String sql = "SELECT COUNT(id) FROM tblCakes"
                    + " WHERE quantity > 0";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<CakeDTO> searchCake(String name, double min, double max, int categoryID, int page) throws Exception {
        //TODO: get paging search result
        List<CakeDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT id,name,descripton,categoryid,createdate,expirationdate,img,price,quantity,isVisible"
                    + " FROM tblCakes"
                    + " WHERE name LIKE ? AND price BETWEEN ? AND ?"
                    + " AND categoryid LIKE ? AND quantity > ? AND isVisible = ?"
                    + " ORDER BY createdate DESC"
                    + " OFFSET (? - 1)* 5 ROWS"
                    + " FETCH NEXT 5 ROWS ONLY";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setDouble(2, min);
            stm.setDouble(3, max);
            stm.setInt(4, categoryID);
            stm.setInt(5, 0);
            stm.setBoolean(6, true);
            stm.setInt(7, page);
            rs = stm.executeQuery();
            while (rs.next()) {                
                result.add(new CakeDTO(rs.getInt("id"), rs.getString("name"),
                        rs.getString("descripton"), rs.getString("img"),
                        rs.getDate("createdate"), rs.getDate("expirationdate"),
                        rs.getDouble("price"), rs.getInt("categoryid"),
                        rs.getInt("quantity"), true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    public List<CakeDTO> searchCakeAdmin(String name, double min, double max, int categoryID, int page) throws Exception {
        //TODO: get paging search result
        List<CakeDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT id,name,descripton,categoryid,createdate,expirationdate,img,price,quantity,isVisible"
                    + " FROM tblCakes"
                    + " WHERE name LIKE ? AND price BETWEEN ? AND ?"
                    + " AND categoryid LIKE ?"
                    + " ORDER BY createdate DESC"
                    + " OFFSET (? - 1)* 5 ROWS"
                    + " FETCH NEXT 5 ROWS ONLY";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setDouble(2, min);
            stm.setDouble(3, max);
            stm.setInt(4, categoryID);
            stm.setInt(5, page);
            rs = stm.executeQuery();
            while (rs.next()) {                
                result.add(new CakeDTO(rs.getInt("id"), rs.getString("name"),
                        rs.getString("descripton"), rs.getString("img"),
                        rs.getDate("createdate"), rs.getDate("expirationdate"),
                        rs.getDouble("price"), rs.getInt("categoryid"),
                        rs.getInt("quantity"), true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    public void subtractQuantity(int id, int quantity) throws Exception{
        try {
            String sql = "UPDATE tblCakes"
                    + " SET quantity = (SELECT quantity FROM tblCakes WHERE id = ?) - ?"
                    + " WHERE id = ?";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, quantity);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
    }
}
