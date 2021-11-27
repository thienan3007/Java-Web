/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.roomtype;

import antdt.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author antru
 */
public class RoomTypeDAO {
    public RoomTypeDTO getRoomType(int roomType) throws SQLException, ClassNotFoundException {
        Connection conn  =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from RoomType where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roomType);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new RoomTypeDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5));
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
