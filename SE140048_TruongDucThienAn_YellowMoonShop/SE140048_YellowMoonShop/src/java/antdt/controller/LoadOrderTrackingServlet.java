/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.ordertracking.OrderTrackingDAO;
import antdt.ordertracking.OrderTrackingDTO;
import antdt.users.UsersDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antru
 */
@WebServlet(name = "LoadOrderTrackingServlet", urlPatterns = {"/orderTracking"})
public class LoadOrderTrackingServlet extends HttpServlet {

    public static final String orderTrackingPage = "orderTracking.jsp";

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
        String id = request.getParameter("orderID");
        HttpSession session = request.getSession();
        UsersDTO usersDTO = (UsersDTO) session.getAttribute("account");
        try {
            if (usersDTO != null) {
                //call dao
                OrderTrackingDAO orderTrackingDAO = new OrderTrackingDAO();
                OrderTrackingDTO orderTrackingDTO = orderTrackingDAO.getOrderTracking(Integer.parseInt(id), usersDTO.getUserID());

                //set attribute
                request.setAttribute("orderTracking", orderTrackingDTO);
            }
        } catch (SQLException ex) {
            log("LoadOrderTrackingServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoadOrderTrackingServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(orderTrackingPage).forward(request, response);
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
