/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.account;

import antdt.accountstatus.AccountStatusDAO;
import antdt.db.DBUtils;
import antdt.role.RoleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author antru
 */
public class AccountDAO {
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    private void closeConnection() throws SQLException {
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
    
    public AccountDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        AccountDTO accountDTO = new AccountDTO();
        RoleDAO roleDAO = new RoleDAO();
        AccountStatusDAO accountStatusDAO = new AccountStatusDAO();
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from Account where email = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
//                String hashPass = rs.getString("password");
//                if (BCrypt.checkpw(password, hashPass)) {
                accountDTO.setEmail(rs.getString("email"));
                accountDTO.setName(rs.getString("name"));
                accountDTO.setRole(roleDAO.getRole(rs.getInt("roleID")));
                accountDTO.setStatus(accountStatusDAO.getAccountStatus(rs.getInt("statusID")));
                //}
            }
        } finally {
            closeConnection();
        }
        return accountDTO;
    }
    
    public boolean InsertAccount(AccountDTO account) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into Account(email, name, password, roleId, statusID) values(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, account.getEmail());
            stm.setString(2, account.getName());
            stm.setString(3, account.getPassword());
            stm.setInt(4, account.getRole().getId());
            stm.setInt(5, account.getStatus().getId());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean findUser(String email) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select email from Account where email = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public AccountDTO findUserByEmail(String email) throws SQLException, ClassNotFoundException {
        try {
            // call dao 
            RoleDAO roleDAO = new RoleDAO();
            AccountStatusDAO accountStatusDAO = new AccountStatusDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Account where email = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new AccountDTO(rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        accountStatusDAO.getAccountStatus(rs.getInt("statusID")),
                        roleDAO.getRole(rs.getInt("roleID")));
            }
        } finally {
            closeConnection();
        }
        return null;
    }
}
