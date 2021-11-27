/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.hotel;

import antdt.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antru
 */
public class HotelDAO {

    public List<HotelDTO> getAllHotelHome() throws SQLException, ClassNotFoundException {
        List<HotelDTO> arrayList = new ArrayList<HotelDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from hotel";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new HotelDTO(rs.getInt("hotelID"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image"),
                        rs.getInt("hotelStatusId")));
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

    public HotelDTO getHotel(int hotelId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from hotel where hotelId = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, hotelId);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new HotelDTO(rs.getInt("hotelID"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image"),
                        rs.getInt("hotelStatusId"));
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
}
