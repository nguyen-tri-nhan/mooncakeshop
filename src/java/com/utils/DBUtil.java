/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Lenovo
 */
public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
        Context ctex = new InitialContext();
        Context cnCtex = (Context) ctex.lookup("java:comp/env");
        DataSource src = (DataSource) cnCtex.lookup("LAB2");
        Connection conn = (Connection) src.getConnection();
        return conn;
    }
}
