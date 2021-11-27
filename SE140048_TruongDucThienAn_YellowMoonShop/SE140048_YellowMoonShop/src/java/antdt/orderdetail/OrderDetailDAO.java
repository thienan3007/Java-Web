/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.orderdetail;

import antdt.Utils.DBUtils;
import antdt.orders.OrdersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author antru
 */
public class OrderDetailDAO {
//    public List<OrderDetailDTO> getAllOrderDetail() throws SQLException, ClassNotFoundException {
//        List<OrderDetailDTO> arrayList = new ArrayList<OrderDetailDTO>();
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = new DBUtils().getConnection();
//            String sql = "select * from tblOrderDetail";
//            stm = conn.prepareStatement(sql);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                arrayList.add(new OrderDetailDTO(rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getInt(3),
//                        rs.getInt(4),
//                        rs.getDouble(5),
//                        rs.getInt(6)));
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

    public boolean insertAllOrderDetailOfAnOrder(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            int i = 0;
            int[] y = null;
            conn = new DBUtils().getConnection();
            String sql = "insert into tblOrderDetail(cakesID, orderID, price, quantity, statusID) values (?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetail()) {
                stm.setInt(1, orderDetailDTO.getCakeDTO().getCakesID());
                stm.setInt(2, ordersDTO.getOrderID());
                stm.setDouble(3, orderDetailDTO.getPrice() * orderDetailDTO.getQuantity());
                stm.setInt(4, orderDetailDTO.getQuantity());
                stm.setInt(5, 1);
                stm.addBatch();
                i++;
                if (i == ordersDTO.getOrderDetail().size()) {
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
