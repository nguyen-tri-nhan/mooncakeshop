/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.daos.CakeDAO;
import com.daos.CategoryDAO;
import com.dtos.CakeDTO;
import com.dtos.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
public class ListAllCakeController extends HttpServlet {

    private static final String SUCCESS = "adminPage.jsp";
    private static final String ERROR = "empty";

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
        //TODO: create a DAO to load all cake which will display cancelled
        String url = ERROR;
        try {
            CakeDAO dao = new CakeDAO();
            CategoryDAO cdao = new CategoryDAO();
            int count = dao.countCake();
            int totalPage;
            if (count % 5 != 0) {
                totalPage = (dao.countCake() / 5) + 1;
            } else {
                totalPage = (dao.countCake() / 5);
            }
            String pageS = request.getParameter("pagenum");
            int page = 1;
            if (pageS != null) {
                page = Integer.parseInt(pageS);
            }
            List<CakeDTO> list = dao.getAll(page);
            List<CategoryDTO> clist = cdao.getCategory();
            if (list != null && clist != null) {
                request.setAttribute("LIST_CAKE_ADM", list);
                request.setAttribute("CATEGORY", clist);
                request.setAttribute("PAGE", totalPage);
                url = SUCCESS;
                //TODO: change to PAGING
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
