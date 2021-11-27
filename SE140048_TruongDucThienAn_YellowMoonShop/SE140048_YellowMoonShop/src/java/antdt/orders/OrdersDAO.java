/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.orders;

import antdt.Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author antru
 */
public class OrdersDAO {
//    private List<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException{
//        List<OrdersDTO> arrayList = new ArrayList<OrdersDTO>();
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs= null;
//        try {
//            conn = new DBUtils().getConnection();
//            String sql = "select * from tblOrders";
//            stm = conn.prepareStatement(sql);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                arrayList.add(new OrdersDTO(rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getString(3),
//                        rs.getDouble(4),
//                        rs.getDate(5),
//                        rs.getInt(6),
//                        rs.getBoolean(7),
//                        rs.getInt(8)));
//            }
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return arrayList;
//    }

    public long insertOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;
        long id = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into tblOrders(address, email, name, orderDate, paymentMethodID, "
                    + "paymentStatus, phone, statusID, totalMoney, userID) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, ordersDTO.getAddress());
            stm.setString(2, ordersDTO.getEmail());
            stm.setString(3, ordersDTO.getName());
            stm.setDate(4, new Date(ordersDTO.getOrderDate().getTime()));
            stm.setInt(5, ordersDTO.getPaymentMethodID());
            stm.setBoolean(6, ordersDTO.isPaymentStatus());
            stm.setString(7, ordersDTO.getPhone());
            stm.setInt(8, ordersDTO.getStatusID());
            stm.setDouble(9, ordersDTO.getTotalMoney());
            if (ordersDTO.getUserID() != null) {
                stm.setInt(10, ordersDTO.getUserID().getUserID());
            } else {
                stm.setString(10, null);
            }
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
    
    public boolean updateQuantity(Map<Integer, Integer> quantityCakeMap) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            int i = 0;
            int[] y = null;
            conn = new DBUtils().getConnection();
            String sql = "update tblCakes set quantity = quantity - ? where id  = ?";
            stm = conn.prepareStatement(sql);
            for (Map.Entry<Integer, Integer> entry : quantityCakeMap.entrySet()) {
                stm.setInt(1, entry.getValue());
                stm.setInt(2, entry.getKey());
                stm.addBatch();
                i++;
                if (i == quantityCakeMap.size()) {
                    y = stm.executeBatch();
                }
            }
            result = y.length > 0;
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
}
