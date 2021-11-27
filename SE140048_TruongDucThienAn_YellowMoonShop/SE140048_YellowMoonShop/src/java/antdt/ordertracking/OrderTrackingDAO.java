/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.ordertracking;

import antdt.Utils.DBUtils;
import antdt.cakes.CakesDAO;
import antdt.orderdetail.OrderDetailDTO;
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
public class OrderTrackingDAO {
    public OrderTrackingDTO getOrderTracking (int orderID, int userID) throws SQLException, ClassNotFoundException {
        OrderTrackingDTO orderTrackingDTO = new OrderTrackingDTO();
        List<OrderDetailDTO> listOrderDetail = new ArrayList<>();
        CakesDAO cakesDAO = new CakesDAO();
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs  = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select o.address, o.name, o.orderDate, o.paymentMethodID, o.paymentStatus, "
                    + "o.totalMoney "
                    + "from tblOrders as o where o.ID = ? and userID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, orderID);
            stm.setInt(2, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                orderTrackingDTO.setAddress(rs.getString("address"));
                orderTrackingDTO.setUsername(rs.getString("name"));
                orderTrackingDTO.setOrderDate(rs.getDate("orderDate"));
                orderTrackingDTO.setPaymentMethod(rs.getInt("paymentMethodID"));
                orderTrackingDTO.setOrderID(orderID);
                orderTrackingDTO.setPaymentStatus(rs.getBoolean("paymentStatus"));
                orderTrackingDTO.setTotalMoney(rs.getDouble("totalMoney"));
                result = true;
            } else {
                orderTrackingDTO = null;
            }
            if (result) {
                sql = "select d.* from tblOrderDetail as d join tblOrders as o on o.ID = d.orderID where o.ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    listOrderDetail.add(new OrderDetailDTO(rs.getInt("ID"),
                            rs.getInt("orderID"),
                            cakesDAO.loadCakesDataByid(rs.getInt("cakesID")),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getInt("statusID")));
                }
                orderTrackingDTO.setListOrderDetail(listOrderDetail);
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
        return orderTrackingDTO;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderTrackingDAO orderTrackingDAO = new OrderTrackingDAO();
        OrderTrackingDTO orderTrackingDTO = orderTrackingDAO.getOrderTracking(16, 3);
        System.out.println(orderTrackingDTO.toString());
        for (OrderDetailDTO orderDetailDTO : orderTrackingDTO.getListOrderDetail()) {
            System.out.println(orderDetailDTO.toString());
        }
    }
}
