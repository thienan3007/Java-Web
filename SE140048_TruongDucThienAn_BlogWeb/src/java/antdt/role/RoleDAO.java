/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.role;

import antdt.db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author antru
 */
public class RoleDAO {
    public RoleDTO getRole(int roleID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Role where roleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roleID);
            rs = stm.executeQuery();
            if(rs.next()) {
                return new RoleDTO(rs.getInt("roleID"), rs.getString("roleName"));
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
