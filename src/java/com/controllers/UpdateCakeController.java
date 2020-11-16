/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.daos.CakeDAO;
import com.daos.LogDAO;
import com.dtos.CakeDTO;
import com.dtos.LogDTO;
import com.dtos.UserDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Lenovo
 */
@MultipartConfig
public class UpdateCakeController extends HttpServlet {

    String END = "ListAllCakeController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = END;
        String err = "Create Date must be before expiration Date";
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");
        try {
            CakeDAO dao = new CakeDAO();
            LogDAO ldao = new LogDAO();
            int id = Integer.parseInt(request.getParameter("txtCakeid"));
            String name = request.getParameter("txtCakeName");
            String description = request.getParameter("txtDescription");
            Date createDate = Date.valueOf(request.getParameter("txtCreateDate"));
            Date expirationDate = Date.valueOf(request.getParameter("txtExpirationDate"));
            if (createDate.after(expirationDate)) {
                throw new Exception(err);
            }
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            int categoryID = Integer.parseInt(request.getParameter("txtCategory"));
            Part filePart = request.getPart("fileIMG");
            String fileName = String.valueOf(id) + ".jpg";
            String imgAddress = "images/" + fileName;
            Double price = Double.parseDouble(request.getParameter("txtCakePrice"));
            boolean visible = request.getParameter("chkVisible").equals("true");
            boolean check = dao.updateCake(new CakeDTO(id, name,
                    description, imgAddress, createDate, expirationDate,
                    price, categoryID, quantity, visible));
            if (check) {
                uploadIMG(fileName, filePart);
                ldao.insertLog(new LogDTO(0, 
                        user.getEmail(), 
                        "update", "Cake " +id+" element",
                        new Timestamp(System.currentTimeMillis())));
            }

        } catch (Exception e) {
            log("Error at SearchServlet " + e.toString());
            request.setAttribute("ERROR_DATE", err);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public void uploadIMG(String fileName, Part filePart) {
        try {
            String applicationPath = "E:\\Project\\Java\\LAB231\\J3LP0011\\web";
            String basePath = applicationPath + File.separator + "images" + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
