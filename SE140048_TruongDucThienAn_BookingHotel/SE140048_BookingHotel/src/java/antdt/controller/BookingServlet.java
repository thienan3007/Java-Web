/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.account.AccountDTO;
import antdt.booking.BookingDAO;
import antdt.booking.BookingDTO;
import antdt.bookingdetail.BookingDetail;
import antdt.bookingdetail.BookingDetailDAO;
import antdt.room.RoomDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    public static final String errorPage = "Cart.jsp";
    public static final String homePage = "HomeShowHotelServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        //get session
        HttpSession httpSession = request.getSession();

        //get attribute 
        BookingDTO bookingDTO = (BookingDTO) httpSession.getAttribute("booking");
        String arrivalDate = request.getParameter("arrivalDate");
        String departureDate = request.getParameter("departureDate");
        String totalPrice = request.getParameter("sum");
        AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("account");

        //call dao
        BookingDAO bookingDAO = new BookingDAO();
        BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();

        //url
        String url = errorPage;
        boolean foundError = false;

        try {
            if (bookingDTO != null) {
                if (arrivalDate.isEmpty()) {
                    foundError = true;
                    request.setAttribute("ARRIVALDATE_ERROR", "arrivalDate_null");
                }
                if (departureDate.isEmpty()) {
                    foundError = true;
                    request.setAttribute("DEPARTURE_ERROR", "departureDate_null");
                }
                if (!arrivalDate.isEmpty() && !departureDate.isEmpty()) {
                    //convert string to date -> after function
                    Date arrivale = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
                    Date departure = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
                    if (!departure.after(arrivale)) {
                        foundError = true;
                        request.setAttribute("DEPARTURE_ERROR_BEFORE_ARRIVAL", "wrong");
                    }
                }
                List<BookingDetail> listErrorRoom = bookingDetailDAO.checkRoom(bookingDTO, arrivalDate, departureDate);
                if (!listErrorRoom.isEmpty()) {
                    foundError = true;
                    request.setAttribute("listErrorRoom", listErrorRoom);
                }

                if (foundError == false) {
                    double totalPriceDouble = Double.parseDouble(totalPrice);

                    //set property for bookingDTO
                    bookingDTO.setAccountEmail(accountDTO.getEmail());
                    bookingDTO.setTotalPrice(totalPriceDouble);
                    bookingDTO.setBookingDate(new Date());

                    //insert booking to database 
                    long id = bookingDAO.insertBooking(bookingDTO);
                    if (id > 0) {
                        bookingDTO.setId((int) id);
                        if (bookingDetailDAO.insertBookingDetail(bookingDTO, arrivalDate, departureDate)) {
                            url = homePage;
                            httpSession.removeAttribute("booking");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("BookingServlet _ SQl " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("BookingServlet _ ClassNotFound " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
