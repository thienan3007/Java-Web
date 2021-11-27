/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.booking;

import antdt.Utils.DBUtils;
import antdt.bookingdetail.BookingDetail;
import antdt.hotel.HotelDAO;
import antdt.room.RoomDAO;
import antdt.roomtype.RoomTypeDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antru
 */
public class BookingDAO {
    public long insertBooking (BookingDTO bookingDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        long id = -1;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into Booking(accountEmail, hotelID, RoomAmount, totalPrice, bookingDate, BookingStattusID) values(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, bookingDTO.getAccountEmail());
            stm.setInt(2, bookingDTO.getHotelDTO().getHotelId());
            stm.setInt(3, bookingDTO.getListBookingDetail().size());
            stm.setDouble(4, bookingDTO.getTotalPrice());
            stm.setDate(5, new Date(bookingDTO.getBookingDate().getTime()));
            stm.setInt(6, 1);
            result = stm.executeUpdate() > 0;
            if (result) {
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return id;
    }
    
    public List<BookingDTO> getAllBookingByAccount(String email) throws SQLException, ClassNotFoundException {
        List<BookingDTO> listBooking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        
        //call dao
        HotelDAO hotelDAO = new HotelDAO();
        
        //convert date to string 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Booking where accountEmail = ? and (BookingStattusID = 1 "
                    + "or BookingStattusID = 2) order by bookingDate DESC";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                listBooking.add(new BookingDTO(rs.getInt(1), 
                        hotelDAO.getHotel(rs.getInt(2)), 
                        rs.getString(3), 
                        getAllBookingDetailByBooking(rs.getInt(1)), 
                        rs.getInt(4), 
                        rs.getDouble(5), 
                        rs.getDate(6), 
                        rs.getInt(7),
                        dateFormat.format(rs.getDate(6))));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listBooking;
    }
    
    public List<BookingDetail> getAllBookingDetailByBooking(int bookingId) throws SQLException, ClassNotFoundException {
        List<BookingDetail> listBookingDetail = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        
        //call dao
        RoomDAO roomDAO = new RoomDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from BookingDetail where bookingID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookingId);
            rs = stm.executeQuery();
            while (rs.next()) {
                listBookingDetail.add(new BookingDetail(rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getDouble(3), 
                        roomDAO.getRoomById(rs.getInt(4)), 
                        roomTypeDAO.getRoomType(rs.getInt(5)), 
                        rs.getBoolean(6), 
                        rs.getDate(7), 
                        rs.getDate(8), 
                        rs.getInt(9)));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listBookingDetail;
    }
    
    public BookingDTO getBookingById(int bookingId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        
        //call dao
        HotelDAO hotelDAO = new HotelDAO();
        
        //convert date to string 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Booking where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookingId);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new BookingDTO(rs.getInt(1), 
                        hotelDAO.getHotel(rs.getInt(2)), 
                        rs.getString(3), 
                        getAllBookingDetailByBooking(rs.getInt(1)), 
                        rs.getInt(4), 
                        rs.getDouble(5), 
                        rs.getDate(6), 
                        rs.getInt(7),
                        dateFormat.format(rs.getDate(6)));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
    
    public boolean checkoutBooking (int bookingId) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update booking set BookingStattusID = 2 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookingId);
            result = stm.executeUpdate() > 0;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public boolean deleteBooking (int bookingId) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update booking set BookingStattusID = 3 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookingId);
            result = stm.executeUpdate() > 0;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public boolean updateStatusOfBookingDetail (int bookingId) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update BookingDetail set status = 'False' where bookingID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookingId);
            result = stm.executeUpdate() > 0;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BookingDAO bookingDAO = new BookingDAO();
        for (BookingDTO bookingDTO : bookingDAO.getAllBookingByAccount("abc@gmail.com")) {
            System.out.println(bookingDTO.toString());
        }
        System.out.println(bookingDAO.getBookingById(12));
    }
}
