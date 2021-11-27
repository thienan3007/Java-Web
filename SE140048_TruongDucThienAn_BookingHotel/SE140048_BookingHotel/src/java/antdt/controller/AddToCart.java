/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.account.AccountDTO;
import antdt.booking.BookingDTO;
import antdt.bookingdetail.BookingDetail;
import antdt.room.RoomDAO;
import antdt.room.RoomDTO;
import antdt.roomtype.RoomTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

    public static final String viewCart = "viewCart";
    public static final String loginPage = "Login.jsp";

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
        String roomId = request.getParameter("roomId");
        HttpSession session = request.getSession();
        BookingDTO bookingDTO = (BookingDTO) session.getAttribute("booking");
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
        int quantity = 1;
        boolean check = true;
        String url = loginPage;

        //call dao
        RoomDAO roomDAO = new RoomDAO();
        try {
            if (accountDTO != null) {
                int roomIdInt = Integer.parseInt(roomId);
                RoomDTO room = roomDAO.getRoomById(roomIdInt);
                if (bookingDTO == null) {
                    bookingDTO = new BookingDTO();
                    List<BookingDetail> arrayList = new ArrayList<BookingDetail>();
                    BookingDetail bookingDetail = new BookingDetail();
                    bookingDetail.setRoom(room);
                    bookingDetail.setRoomType(room.getRoomType());
                    bookingDetail.setQuantity(quantity);
                    bookingDetail.setPrice(room.getPrice() * quantity);
                    arrayList.add(bookingDetail);
                    bookingDTO.setHotelDTO(room.getHotel());
                    bookingDTO.setListBookingDetail(arrayList);
                    session.setAttribute("booking", bookingDTO);
                    session.setAttribute("hotel", room.getHotel());
                    url = viewCart;
                } else {
                    List<BookingDetail> listBookingDetail = bookingDTO.getListBookingDetail();
                    for (BookingDetail bookingDetail1 : listBookingDetail) {
                        if (bookingDetail1.getRoom().getRoomID() == roomIdInt) {
                            request.setAttribute("QUANTITY_ERROR", "1");
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        BookingDetail bookingDetail = new BookingDetail();
                        bookingDetail.setRoomType(room.getRoomType());
                        bookingDetail.setRoom(room);
                        bookingDetail.setQuantity(quantity);
                        bookingDetail.setPrice(room.getPrice() * quantity);
                        listBookingDetail.add(bookingDetail);
                        bookingDTO.setHotelDTO(room.getHotel());
                        bookingDTO.setListBookingDetail(listBookingDetail);
                        session.setAttribute("booking", bookingDTO);
                        request.setAttribute("hotel", room.getHotel());
                        url = viewCart;
                    }
                }
            }
        } catch (SQLException ex) {
            log("AddToCart _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("AddToCart _ ClassNotFound " + ex.getMessage());
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
