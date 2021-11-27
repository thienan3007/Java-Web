/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.account;

import antdt.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author antru
 */
public class AccountDAO {
    public AccountDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Account where email = ? and password = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new AccountDTO(rs.getString("email").trim(),
                        rs.getString("phone").trim(),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDate("createDate"),
                        rs.getString("password").trim(),
                        rs.getInt("accountRoleID"));
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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AccountDAO accountDAO = new AccountDAO();
        System.out.println(accountDAO.checkLogin("abc@gmail.com", "123").toString());
    }
}
