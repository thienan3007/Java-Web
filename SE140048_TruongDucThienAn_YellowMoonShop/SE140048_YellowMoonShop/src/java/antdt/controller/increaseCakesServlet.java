/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.CakesDAO;
import antdt.orderdetail.OrderDetailDTO;
import antdt.orders.OrdersDTO;
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
@WebServlet(name = "increaseCakesServlet", urlPatterns = {"/add"})
public class increaseCakesServlet extends HttpServlet {
    public static final String viewCart = "viewCart";
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
        try {
            CakesDAO cakesDAO = new CakesDAO();            
            int quantity = 1;
            int id = Integer.parseInt(request.getParameter("id"));
            int cakeQuantity = cakesDAO.getQuantityOfCakeByID(id);
            HttpSession session = request.getSession();
            OrdersDTO ordersDTO = (OrdersDTO) session.getAttribute("order");
            for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetail()) {
                if(orderDetailDTO.getCakeDTO().getCakesID() == id) {
                    if (orderDetailDTO.getQuantity() + 1 <= cakeQuantity) {
                        orderDetailDTO.setQuantity(orderDetailDTO.getQuantity() + quantity);
                    } else {
                        request.setAttribute("QUANTITY_ERROR", cakeQuantity);
                    }
                }
            }
            session.setAttribute("order", ordersDTO);
        } catch (SQLException ex) {
            log("increaseCakesServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("increaseCakesServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(viewCart).forward(request, response);
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
