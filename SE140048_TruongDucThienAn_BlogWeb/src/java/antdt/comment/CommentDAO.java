/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.comment;

import antdt.account.AccountDAO;
import antdt.db.DBUtils;
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
public class CommentDAO {
    Connection conn;
    PreparedStatement stm;
    ResultSet rs;

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
    
    public List<CommentDTO> GetAllCommentsOfAritcleById(int articleID) throws SQLException, ClassNotFoundException {
        List<CommentDTO> arrayList = new ArrayList<CommentDTO>();
        try {
            //calldao
            AccountDAO accountDAO = new AccountDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Comment where articleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, articleID);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new CommentDTO(rs.getInt("id"),
                        rs.getString("commentContent"),
                        accountDAO.findUserByEmail(rs.getString("userEmail")),
                        rs.getDate("commentDate"),
                        rs.getInt("statusID")));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }
    
    public boolean insertCommentByAricleId(CommentDTO commentDTO, int articleID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into Comment(articleID,commentContent,commentDate,statusID,userEmail) values(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, articleID);
            stm.setString(2, commentDTO.getContent());
            stm.setDate(3, new Date(commentDTO.getCommentDate().getTime()));
            stm.setInt(4, commentDTO.getStatusID());
            stm.setString(5, commentDTO.getUser().getEmail());
            result =  stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
