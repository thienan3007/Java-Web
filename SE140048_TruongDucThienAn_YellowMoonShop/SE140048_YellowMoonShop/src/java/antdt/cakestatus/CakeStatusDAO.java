/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cakestatus;

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
public class CakeStatusDAO {
    public List<CakeStatusDTO> getAllCakeStatus() throws SQLException, ClassNotFoundException {
        List<CakeStatusDTO> arrayList = new ArrayList<CakeStatusDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            String sql = " select * from tblCakesStatus";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CakeStatusDTO(rs.getInt(1),
                        rs.getString(2)));
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
}
