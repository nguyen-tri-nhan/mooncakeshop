/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.daos.AddressDAO;
import com.daos.CakeDAO;
import com.daos.OrderDAO;
import com.daos.OrderDetailDAO;
import com.dtos.AddressDTO;
import com.dtos.CakeDTO;
import com.dtos.CartDTO;
import com.dtos.OrderDTO;
import com.dtos.OrderDetailDTO;
import com.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
public class OrderController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "index.jsp";

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
        HttpSession session = request.getSession();
        try {
            List<OrderDetailDTO> details = new ArrayList<>();
            AddressDAO addrDao = new AddressDAO();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            AddressDTO addr = new AddressDTO("", user.getEmail(),
                    request.getParameter("txtFullName"),
                    request.getParameter("txtPhone"),
                    request.getParameter("txtAddress"));
            boolean chkAddr = addrDao.insertAddress(addr);
            int addrID = addrDao.currentID();
            OrderDTO order = new OrderDTO(0, user.getEmail(), addrID, new Timestamp(System.currentTimeMillis()), false, false, 1);
            OrderDAO orDao = new OrderDAO();
            boolean chkOrder = orDao.insertOrder(order);
            int orderID = orDao.currentID();
            CakeDAO cakeDao = new CakeDAO();
            for (CakeDTO cake : cart.getCart().values()) {
                details.add(new OrderDetailDTO(0, orderID, cake.getId(), cake.getQuantity()));
                cakeDao.subtractQuantity(cake.getId(), cake.getQuantity());
            }
            OrderDetailDAO odetailDao = new OrderDetailDAO();
            boolean chkDetail = odetailDao.insertOrderDetails(details);
            if (!chkDetail){
                addrDao.deleteAddress(addrID);
                orDao.deleteOrder(orderID);
            }
            if (chkAddr && chkOrder){
                url = SUCCESS;
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
