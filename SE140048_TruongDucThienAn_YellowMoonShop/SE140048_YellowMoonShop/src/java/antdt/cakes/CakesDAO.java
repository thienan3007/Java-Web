/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cakes;

import antdt.Utils.DBUtils;
import antdt.cakestatus.CakeStatusDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antru
 */
public class CakesDAO {

    public List<CakesDTO> getAllcakes() throws SQLException, ClassNotFoundException {
        List<CakesDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes where statusID = 1 and quantity > 0";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CakesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CakesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return list;
    }

    public int getQuantityOfCakeByID(int cakeID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select quantity from tblCakes where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cakeID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
        return -1;
    }

    public List<CakesDTO> getAllcakesAdmin() throws SQLException, ClassNotFoundException {
        List<CakesDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CakesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CakesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return list;
    }

    public List<CakesDTO> searchCakesByMoney(double min, double max) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes where price < ? and price > ? and quantity > 0 and "
                    + "statusID = 1";
            stm = conn.prepareStatement(sql);
            stm.setDouble(1, max);
            stm.setDouble(2, min);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public List<CakesDTO> searchCakesByCategory(int categoryID) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes where categoryID = ?";
            stm = conn.prepareStatement(sql);
            stm.setDouble(1, categoryID);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public List<CakesDTO> searchCakesByName(String name) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes where name like ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public boolean addCakes(CakesDTO cakesDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into tblCakes(name, price, createDate, expirationDate, categoryID, quantity, statusID, "
                    + "description, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, cakesDTO.getName());
            stm.setDouble(2, cakesDTO.getPrice());
            stm.setDate(3, new java.sql.Date(cakesDTO.getCreateDate().getTime()));
            stm.setDate(4, new java.sql.Date(cakesDTO.getExpirationDate().getTime()));
            stm.setInt(5, cakesDTO.getCategoryID());
            stm.setInt(6, cakesDTO.getQuantity());
            stm.setInt(7, 1);
            stm.setString(8, cakesDTO.getDescription());
            stm.setString(9, cakesDTO.getImage());

            int result = stm.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public CakesDTO loadCakesDataByid(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect db 
            conn = new DBUtils().getConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String sql = "SELECT * "
                        + "FROM tblCakes "
                        + "WHERE ID = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                //4. execute Query 
                rs = stm.executeQuery();

                //5. process resultSet
                if (rs.next()) {
                    return new CakesDTO(rs.getInt("ID"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getDate("createDate"),
                            rs.getDate("expirationDate"),
                            rs.getInt("categoryID"),
                            rs.getInt("quantity"),
                            rs.getInt("statusID"),
                            rs.getString("description"),
                            rs.getString("image"));
                } // end more then one record are returned 
            } // end connection has opened

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

    public boolean updateCakesByID(CakesDTO cake) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connect db 
            conn = new DBUtils().getConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String sql = "update tblCakes set name = ?, \n"
                        + "image = ?, createDate = ?, expirationDate = ?, price = ?, quantity = ?, categoryID = ?, "
                        + "statusID = ?, description = ? where ID = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(sql);
                stm.setString(1, cake.getName());
                stm.setString(2, cake.getImage());
                stm.setDate(3, new Date(cake.getCreateDate().getTime()));
                stm.setDate(4, new Date(cake.getExpirationDate().getTime()));
                stm.setDouble(5, cake.getPrice());
                stm.setInt(6, cake.getQuantity());
                stm.setInt(7, cake.getCategoryID());
                stm.setInt(8, cake.getStatusID());
                stm.setString(9, cake.getDescription());
                stm.setInt(10, cake.getCakesID());
                //4. execute Query 
                result = stm.executeUpdate() > 0;
            } // end connection has opened

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

    public int getTotalCakeHomePage() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(ID) from tblCakes where statusID = 1 and "
                    + "quantity > 0";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
    }

    public int getTotalCakeHomePageSearchName(String cakeName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(ID) from tblCakes where statusID = 1 and "
                    + "quantity > 0 and name like ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + cakeName + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
    }

    public int getTotalCakeAdminPage() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(ID) from tblCakes";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
    }

    public int getTotalCakeCategory(int categoryID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(ID) from tblCakes where statusID = 1 and "
                    + "quantity > 0 and categoryID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, categoryID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
    }

    public int getTotalCakePrice(double priceMin, double priceMax) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(ID) from tblCakes where statusID = 1 and "
                    + "quantity > 0 and price > ? and price < ?";
            stm = conn.prepareStatement(sql);
            stm.setDouble(1, priceMin);
            stm.setDouble(2, priceMax);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

        return 0;
    }

    public List<CakesDTO> pagingAllCakes(int index) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "SELECT * from tblCakes where quantity > 0 and statusID = 1 "
                    + "order by createDate desc "
                    + "offset ? rows fetch next 6 rows only;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public List<CakesDTO> pagingAllCakesSearchName(int index, String cakeName) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCakes where quantity > 0 and statusID = 1 and name like ? \n"
                    + "order by createDate desc\n"
                    + "offset ? rows fetch next 6 rows only";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + cakeName + "%");
            stm.setInt(2, (index - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public List<CakesDTO> pagingAllCakesAdminPage(int index) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "SELECT * from tblCakes "
                    + "order by createDate desc "
                    + "offset ? rows fetch next 6 rows only;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public List<CakesDTO> pagingAllCakesCategory(int index, int categoryID) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "SELECT * from tblCakes where quantity > 0 and statusID = 1 and categoryID = ? "
                    + "order by createDate desc "
                    + "offset ? rows fetch next 6 rows only;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, categoryID);
            stm.setInt(2, (index - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public boolean updateQuantityCake(int cakeID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update tblCakes set quantity = quantity - 1 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cakeID);
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

    public List<CakesDTO> pagingAllCakesPrice(int index, double priceMin, double priceMax) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "SELECT * from tblCakes where quantity > 0 and statusID = 1 "
                    + "and price < ? and price > ? "
                    + "order by createDate desc "
                    + "offset ? rows fetch next 6 rows only;";
            stm = conn.prepareStatement(sql);
            stm.setDouble(1, priceMax);
            stm.setDouble(2, priceMin);
            stm.setInt(3, (index - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakesDTO(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDate("createDate"),
                        rs.getDate("expirationDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("quantity"),
                        rs.getInt("statusID"),
                        rs.getString("description"),
                        rs.getString("image")));
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<CakesDTO> arrayList = new ArrayList<CakesDTO>();
        CakesDAO dao = new CakesDAO();
        
        System.out.println(dao.pagingAllCakesSearchName(1, "trung").size());
//        System.out.println(dao.getQuantityOfCakeByID(12));
        System.out.println(dao.getTotalCakeHomePageSearchName("trung"));
        for (CakesDTO pagingCake : dao.pagingAllCakesSearchName(1, "trung")) {
            System.out.println(pagingCake.toString());
        }
    }
}
