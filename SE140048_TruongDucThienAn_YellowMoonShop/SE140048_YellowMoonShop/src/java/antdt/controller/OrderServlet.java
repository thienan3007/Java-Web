/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.orderdetail.OrderDetailDAO;
import antdt.orderdetail.OrderDetailDTO;
import antdt.orders.OrdersDAO;
import antdt.orders.OrdersDTO;
import antdt.users.UserFormErrors;
import antdt.users.UsersDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    public static final String homePage = "HomeServlet";
    public static final String errorPage = "Cart.jsp";

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
        request.setCharacterEncoding("UTF-8");
        //get parameter
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String str = request.getParameter("payment");
        int payment = Integer.parseInt(str);
        HttpSession httpSession = request.getSession();
        double totalMoney = (Double) httpSession.getAttribute("sum");
        UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("account");
        OrdersDTO ordersDTO = (OrdersDTO) httpSession.getAttribute("order");

        //create errorDTO
        UserFormErrors userFormErrors = new UserFormErrors();

        //regex
        Pattern phonePattern = Pattern.compile("^[0-9]+$");
        boolean foundErrors = false;

        //url
        String url = errorPage;
        try {
            if (ordersDTO != null) {
                if (usersDTO == null) {
                    //errors
                    if (phone.length() == 0 || phone.length() > 10) {
                        foundErrors = true;
                        userFormErrors.setPhoneError("Phone no matched!!!!");
                    } else if (!Pattern.matches("^[0-9]+$", phone)) {
                        foundErrors = true;
                        userFormErrors.setPhoneError("Could not enter characters for phone numbers!!!!");
                    }
                    if (name.length() == 0 || name.length() > 100) {
                        foundErrors = true;
                        userFormErrors.setNameError("Name no matched!!!!");
                    } else if (Pattern.compile("^[0-9]+$.*").matcher(name).find()) {
                        foundErrors = true;
                        userFormErrors.setNameError("Name must not have number!!!!");
                    }
                    if (address.length() == 0) {
                        foundErrors = true;
                        userFormErrors.setAddressError("Address no macthed!!!!");
                    }
                    if (email.length() == 0 || email.length() > 100) {
                        foundErrors = true;
                        userFormErrors.setEmail("Email no macthed!!!");
                    } else if (!Pattern.compile("@.*").matcher(email).find()) {
                        foundErrors = true;
                        userFormErrors.setEmail("Email must have @!!!!");
                    }

                    // if have errors
                    if (foundErrors) {
                        //set attribute for erros
                        request.setAttribute("FORM_ERRORS", userFormErrors);
                    } else {
                        ordersDTO.setAddress(address);
                        ordersDTO.setName(name);
                        ordersDTO.setEmail(email);
                        ordersDTO.setUserID(null);
                        ordersDTO.setPaymentMethodID(payment);
                        ordersDTO.setPhone(phone);
                        ordersDTO.setTotalMoney(totalMoney);
                        ordersDTO.setOrderDate(new Date());
                        ordersDTO.setStatusID(1);
                        ordersDTO.setPaymentStatus(true);
                    }
                } else {
                    ordersDTO.setAddress(usersDTO.getAddress());
                    ordersDTO.setName(usersDTO.getFullname());
                    ordersDTO.setEmail(null);
                    ordersDTO.setUserID(usersDTO);
                    ordersDTO.setPaymentMethodID(payment);
                    ordersDTO.setPhone(usersDTO.getPhone());
                    ordersDTO.setTotalMoney(totalMoney);
                    ordersDTO.setOrderDate(new Date());
                    ordersDTO.setStatusID(1);
                    ordersDTO.setPaymentStatus(true);
                }
                if (foundErrors == false || usersDTO != null) {
                    //call dao orders
                    OrdersDAO ordersDAO = new OrdersDAO();

                    //call dao orderDetail
                    OrderDetailDAO orderdetailDAO = new OrderDetailDAO();
                    long id = ordersDAO.insertOrder(ordersDTO);
                    if (id > 0) {
                        ordersDTO.setOrderID((int) id);
                        if (orderdetailDAO.insertAllOrderDetailOfAnOrder(ordersDTO)) {
                            // get the quantity of each cake in the orders
                            Map<Integer, Integer> quantityCakeMap = new HashMap<>();
                            for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetail()) {
                                if (!quantityCakeMap.containsKey(orderDetailDTO.getCakeDTO().getCakesID())) {
                                    quantityCakeMap.put(orderDetailDTO.getCakeDTO().getCakesID(), orderDetailDTO.getQuantity());
                                }
                            }
                            //update quantity of cake
                            if (ordersDAO.updateQuantity(quantityCakeMap)) {
                                url = homePage;
                                httpSession.removeAttribute("order");
                                request.setAttribute("orderID", id);
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            log("OrderServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("OrderServlet _ ClassNotFound " + ex.getMessage());
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
