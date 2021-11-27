/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import antdt.orderdetail.OrderDetailDTO;
import antdt.orders.OrdersDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {

    public static final String viewCart = "viewCart";
    public static final String homePage = "HomeServlet";

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
        CakesDAO cakesDAO = new CakesDAO();
        String url = homePage;
        try {
            int quantity = 1;
            int id;

            if (request.getParameter("cakesId") != null) {

                id = Integer.parseInt(request.getParameter("cakesId"));

                int cakeQuantity = cakesDAO.getQuantityOfCakeByID(id);

                CakesDTO cakesDTO = cakesDAO.loadCakesDataByid(id);

                boolean check = false;
                if (cakesDTO != null) {
                    int cakeID = cakesDAO.getQuantityOfCakeByID(id);

                    HttpSession httpSession = request.getSession();
                    if (httpSession.getAttribute("order") == null) {
                        OrdersDTO ordersDTO = new OrdersDTO();
                        List<OrderDetailDTO> arrayList = new ArrayList<OrderDetailDTO>();
                        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                        orderDetailDTO.setQuantity(quantity);
                        orderDetailDTO.setCakeDTO(cakesDTO);
                        orderDetailDTO.setPrice(cakesDTO.getPrice());
                        arrayList.add(orderDetailDTO);
                        ordersDTO.setOrderDetail(arrayList);
                        check = true;
                        httpSession.setAttribute("order", ordersDTO);
                    } else {
                        OrdersDTO ordersDTO = (OrdersDTO) httpSession.getAttribute("order");
                        List<OrderDetailDTO> arrayList = ordersDTO.getOrderDetail();
                        boolean check1 = false;
                        for (OrderDetailDTO orderDetailDTO : arrayList) {
                            if (orderDetailDTO.getCakeDTO().getCakesID() == cakesDTO.getCakesID()) {
                                if (orderDetailDTO.getQuantity() + 1 < cakeQuantity) {
                                    orderDetailDTO.setQuantity(orderDetailDTO.getQuantity() + quantity);                                    
                                    check = true;
                                }
                                check1 = true;
                            }
                        }
                        if (check1 == false) {
                            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                            orderDetailDTO.setQuantity(quantity);
                            orderDetailDTO.setCakeDTO(cakesDTO);
                            orderDetailDTO.setPrice(cakesDTO.getPrice());
                            arrayList.add(orderDetailDTO);
                            check = true;
                        }
                        httpSession.setAttribute("order", ordersDTO);
                    }
                    if (check) {
                        url = viewCart;
                    } else {
                        request.setAttribute("QUANTITY_ERROR", cakeID);
                    }   
                }
            }

        } catch (SQLException ex) {
            log("addToCart _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("addToCart _ ClassNotFound " + ex.getMessage());
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
