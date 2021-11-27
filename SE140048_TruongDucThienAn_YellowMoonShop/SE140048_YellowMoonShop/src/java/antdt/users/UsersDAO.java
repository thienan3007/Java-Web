/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.users;

import antdt.Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antru
 */
public class UsersDAO {
    public List<UsersDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        List<UsersDTO> arrayList = new ArrayList<UsersDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblUsers";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new UsersDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getBoolean(8)));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return arrayList;
    }
    public static UsersDTO checkLogin(int userID, String password) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "Select * from tblUsers where userID = ? and password = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new UsersDTO(rs.getInt("userID"),
                        rs.getString("fullname"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getDate("createDate"),
                        rs.getInt("roleID"),
                        rs.getBoolean("status"));              
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }
    
    public boolean findUser(int id) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select userid from tblUsers where userid = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = true;
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
        return result;
    }
    public boolean insertNewAccountUser(UsersDTO usersDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn =  null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into tblUsers(userID, fullname, phone, address, createDate, password, roleID, status) values(?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, usersDTO.getUserID());
            stm.setString(2, usersDTO.getFullname());
            stm.setString(3, usersDTO.getPhone());
            stm.setString(4, usersDTO.getAddress());
            stm.setDate(5, new Date(usersDTO.getCreateDate().getTime()));
            stm.setString(6, usersDTO.getPassword());
            stm.setInt(7, usersDTO.getRoleID());
            stm.setBoolean(8, usersDTO.isStatus());
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        UsersDAO usersDAO = new UsersDAO();
        UsersDTO usersDTO = usersDAO.checkLogin(1, "123");
        System.out.println(usersDTO);
    }
    
}






















