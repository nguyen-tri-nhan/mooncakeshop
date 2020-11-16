/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.daos.UserDAO;
import com.dtos.ErrorDTO;
import com.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
public class RegisterController extends HttpServlet {
    public static final String ERROR = "register.jsp";
    public static final String SUCCESS = "success.html";
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
        String url = ERROR;
        try {
            ErrorDTO errordto = new ErrorDTO("", "");
            boolean check = true;
            String email = request.getParameter("txtEmail").trim();
            String firstname = request.getParameter("txtFirstname").trim();
            String lastname = request.getParameter("txtLastname").trim();
            String password = request.getParameter("txtPassword");
            String repass = request.getParameter("txtRepassword");
            if (!password.equals(repass)) {
                errordto.setPassword("Password and re-attemp password doesnot match");
                check = false;
            }
            UserDAO dao = new UserDAO();
            if (dao.isExistedUser(email)) {
                errordto.setUsername("This email is registed before. Are you own? <a href=\"login.jsp\">Login now?</a></p>");
                check = false;
            }
            if (check) {
                Date today = new Date(System.currentTimeMillis());
                UserDTO dto = new UserDTO(email, firstname, lastname, password, today, 0, true);
                if (dao.createUser(dto)) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_USER_ATTRIBUTE", errordto);
            }
        } catch (Exception e) {
            log("Error at SearchServlet " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
