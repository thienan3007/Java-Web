/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.booking.BookingDTO;
import antdt.hotel.HotelDAO;
import antdt.hotel.HotelDTO;
import antdt.room.RoomDAO;
import antdt.room.RoomDTO;
import antdt.roomtype.RoomTypeDTO;
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
@WebServlet(name = "RoomServlet", urlPatterns = {"/room"})
public class RoomServlet extends HttpServlet {

    public static final String roomPage = "listroom.jsp";

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

        //get attribute
        String hotelId = request.getParameter("hotelId");
        HttpSession session = request.getSession();
        BookingDTO bookingDTO = (BookingDTO) session.getAttribute("booking");

        //call dao
        RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        try {
            //just hotel id
            int id = Integer.parseInt(hotelId);
            List<RoomDTO> arrayList = roomDAO.getAllRoomByHotelId(id);
            List<RoomDTO> c = new ArrayList<>(arrayList);
            if (bookingDTO != null) {
                
                List<RoomDTO> d = new ArrayList<>();
                bookingDTO.getListBookingDetail().forEach((bookingDetail) -> {
                    d.add(bookingDetail.getRoom());
                });
                c.removeAll(d);
            }
            List<RoomTypeDTO> arrayList1 = roomDAO.getAllRoomTypeOfThatHotel(id);
            List<HotelDTO> hotelList = hotelDAO.getAllHotelHome();

            request.setAttribute("roomList", c);
            request.setAttribute("listRoomType", arrayList1);
            request.setAttribute("hotelList", hotelList);
            request.setAttribute("hotelId", hotelId);

        } catch (SQLException ex) {
            log("RoomServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("RoomServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(roomPage).forward(request, response);
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
