/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.category;

import antdt.Utils.DBUtils;
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
public class CategoryDAO {
    public List<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException{
        List<CategoryDTO> arrayList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from tblCategory";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CategoryDTO(rs.getInt("categoryID"),
                        rs.getString("categoryName")));
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> arrayList = categoryDAO.getAllCategory();
        for (CategoryDTO categoryDTO : arrayList) {
            System.out.println(categoryDTO.toString());
        }

    }
    
}
