/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.account.AccountDTO;
import antdt.booking.BookingDAO;
import antdt.booking.BookingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "loadBookingHistory", urlPatterns = {"/loadBookingHistory"})
public class loadBookingHistory extends HttpServlet {
    public static final String viewHistory = "bookingHistory.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //session
        HttpSession httpSession = request.getSession();
        
        //get attribute
        AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");
        
        //call dao
        BookingDAO bookingDAO = new BookingDAO();
        try {
            if (accountDTO != null) {
                List<BookingDTO> listBooking = bookingDAO.getAllBookingByAccount(accountDTO.getEmail().trim());
                if (listBooking.isEmpty()) {
                    request.setAttribute("BOOKING_HISTORY_ERROR", "null");
                } else {
                    request.setAttribute("listBooking", listBooking);
                }               
            }
        } catch (SQLException ex) {
            log("loadBookingHistory _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("loadBookingHistory _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(viewHistory).forward(request, response);
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
