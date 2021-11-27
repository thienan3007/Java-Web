/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.accountstatus;

import antdt.db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author antru
 */
public class AccountStatusDAO {
    public AccountStatusDTO getAccountStatus(int statusID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs =null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from AccountStatus where accountStatusID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, statusID);
            rs = stm.executeQuery();
            if(rs.next()) {
                return new AccountStatusDTO(rs.getInt("accountStatusID"), rs.getString("accountStatusName"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
        return null;
    }
}
