/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.room;

import antdt.Utils.DBUtils;
import antdt.hotel.HotelDAO;
import antdt.roomtype.RoomTypeDAO;
import antdt.roomtype.RoomTypeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antru
 */
public class RoomDAO {

    public List<RoomDTO> getAllRoomByHotelId(int hotelId) throws SQLException, ClassNotFoundException {
        List<RoomDTO> arrayList = new ArrayList<RoomDTO>();
        HotelDAO hotelDAO = new HotelDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Room where hotelID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, hotelId);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new RoomDTO(rs.getInt(1),
                        rs.getString(2),
                        hotelDAO.getHotel(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getDouble(5),
                        roomTypeDAO.getRoomType(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
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
        return arrayList;
    }

    public List<RoomDTO> getAllRoomByRoomTypeId(int roomTypeId, int hotelId) throws SQLException, ClassNotFoundException {
        List<RoomDTO> arrayList = new ArrayList<RoomDTO>();
        HotelDAO hotelDAO = new HotelDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Room where RoomTypeId = ? and hotelId = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roomTypeId);
            stm.setInt(2, hotelId);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new RoomDTO(rs.getInt(1),
                        rs.getString(2),
                        hotelDAO.getHotel(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getDouble(5),
                        roomTypeDAO.getRoomType(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
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
        return arrayList;
    }

    public List<RoomDTO> getAllRoomByArrivalDateAndDepartureDay(String arrivalDate, String departureDay, int roomTypeID, int hotelId) throws SQLException, ClassNotFoundException {
        List<RoomDTO> arrayList = new ArrayList<RoomDTO>();
        HotelDAO hotelDAO = new HotelDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select r.* from Room as r where not exists (select roomId from BookingDetail as d "
                    + "where ((?  between d.DateFrom and d.DateTo) or\n"
                    + "(? between d.DateFrom and d.DateTo)) and r.roomId = d.roomId and d.status = 'True') "
                    + "and r.hotelID = ?";
            if (roomTypeID != 0) {
                sql += " and r.RoomTypeId = ?";
            }
            stm = conn.prepareStatement(sql);
            stm.setString(1, arrivalDate);
            stm.setString(2, departureDay);
            stm.setInt(3, hotelId);
            if (roomTypeID != 0) {
                stm.setInt(4, roomTypeID);
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new RoomDTO(rs.getInt(1),
                        rs.getString(2),
                        hotelDAO.getHotel(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getDouble(5),
                        roomTypeDAO.getRoomType(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
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
        return arrayList;
    }

    public List<RoomTypeDTO> getAllRoomTypeOfThatHotel(int hotelId) throws SQLException, ClassNotFoundException {
        List<RoomTypeDTO> arrayList = new ArrayList<RoomTypeDTO>();
        List<Integer> roomTypeID = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select distinct RoomTypeid from Room where hotelID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, hotelId);
            rs = stm.executeQuery();
            while (rs.next()) {
                roomTypeID.add(rs.getInt(1));
            }
            for (int i = 0; i < roomTypeID.size(); i++) {
                sql = "select * from roomtype where id = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, roomTypeID.get(i));
                rs = stm.executeQuery();
                while (rs.next()) {
                    arrayList.add(new RoomTypeDTO(rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getString(5)));
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
        return arrayList;
    }

    public int getQuantityOfAllRoomLeft(int roomTypeId, int hotelId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(r.roomId) as count from Room as r where not exists "
                    + "(select roomId from BookingDetail as d where r.roomId = d.roomId) "
                    + "and r.hotelID = ? ";
            if (roomTypeId != 0) {
                sql += "and r.RoomTypeId = ?";
            }
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1, hotelId);
            if (roomTypeId != 0) {
                stm.setInt(2, roomTypeId);
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
        return -1;
    }
    
    public RoomDTO getRoomById(int roomId) throws SQLException, ClassNotFoundException {
        HotelDAO hotelDAO = new HotelDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Room where roomId = ?";
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1, roomId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new RoomDTO(rs.getInt(1),
                        rs.getString(2),
                        hotelDAO.getHotel(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getDouble(5),
                        roomTypeDAO.getRoomType(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        RoomDAO roomDAO = new RoomDAO();
//        for (RoomDTO roomTypeDTO : roomDAO.getAllRoomByArrivalDateAndDepartureDay("2021-01-04 14:45", "2021-01-15", 0, 1)) {
//            System.out.println(roomTypeDTO.toString());
//        }
        String date = "2021-01-01 14:45";
        String after = "2021-02-02";
        Date time = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date timeafter = new SimpleDateFormat("yyyy-MM-dd").parse(after);
        if (timeafter.after(time)) {
            System.out.println(time);
        }
    }
}
