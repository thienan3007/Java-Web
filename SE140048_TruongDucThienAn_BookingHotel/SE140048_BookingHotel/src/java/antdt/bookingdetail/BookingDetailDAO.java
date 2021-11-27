/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.bookingdetail;

import antdt.Utils.DBUtils;
import antdt.booking.BookingDTO;
import antdt.room.RoomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antru
 */
public class BookingDetailDAO {

    public boolean insertBookingDetail(BookingDTO bookingDTO, String dateFrom, String dateTo) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            int i = 0;
            int[] y = null;
            conn = new DBUtils().getConnection();
            String sql = "insert into BookingDetail(bookingID, DateFrom, DateTo, price, quantity, "
                    + "roomId, roomType, status) values(?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            for (BookingDetail bookingDetail : bookingDTO.getListBookingDetail()) {
                stm.setInt(1, bookingDTO.getId());
                stm.setString(2, dateFrom);
                stm.setString(3, dateTo);
                stm.setDouble(4, bookingDetail.getRoom().getPrice());
                stm.setInt(5, 1);
                stm.setInt(6, bookingDetail.getRoom().getRoomID());
                stm.setInt(7, bookingDetail.getRoom().getRoomType().getId());
                stm.setBoolean(8, true);
                stm.addBatch();
                i++;
                if (i == bookingDTO.getListBookingDetail().size()) {
                    y = stm.executeBatch();
                }

            }
            result = y.length > 0;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

    public List<BookingDetail> checkRoom(BookingDTO bookingDTO, String dateFrom, String dateTo) throws SQLException, ClassNotFoundException, ParseException {
        //list error room
        List<BookingDetail> listRoom = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        //convert string to date 
        Date arrivale = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        Date departure = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
        
        //Convert date to string
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");  
        try {
            conn = new DBUtils().getConnection();
            String sql = "select DateFrom, DateTo from BookingDetail where status = 'True' and roomId = ?";
            stm = conn.prepareStatement(sql);
            for (BookingDetail bookingDetail : bookingDTO.getListBookingDetail()) {
                int roomId = bookingDetail.getRoom().getRoomID();
                stm.setInt(1, roomId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    if ((arrivale.after(rs.getDate(1)) && arrivale.before(rs.getDate(2))) || departure.before(rs.getDate(2))) {
                        bookingDetail.setDateFromString(dateFormat.format(rs.getDate(1)));
                        bookingDetail.setDateToString(dateFormat.format(rs.getDate(2)));
                        listRoom.add(bookingDetail);
                    }
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
        return listRoom;
    }
}
