/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.article;

import antdt.account.AccountDAO;
import antdt.accountstatus.AccountStatusDTO;
import antdt.articlestatus.ArticleStatusDTO;
import antdt.comment.CommentDAO;
import antdt.db.DBUtils;
import antdt.role.RoleDTO;
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
public class ArticleDAO {

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

    public boolean InsertArticle(ArticleDTO article) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "insert into Article(articelContent,authorEmail, Description,postingDate,"
                    + "statusID,title) values(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, article.getContent());
            stm.setString(2, article.getAuthor().getEmail());
            stm.setString(3, article.getDescription());
            stm.setDate(4, new Date(article.getPostingDate().getTime()));
            stm.setInt(5, article.getStatusID());
            stm.setString(6, article.getTitle());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticleAdmin() throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where not statusID = 2";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticleAdminByKeyword(String keyword) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where articelContent like ? and not statusID = 2";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticleMember() throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where statusID = 3";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticleMemberByKeyword(String keyword) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where statusID = 3 and articelContent like ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int getTotalArticleMemberByKeywordName(String keyword) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where title like ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticleByStatus(int cid) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            conn = new DBUtils().getConnection();
            String sql = "select count(id) from Article where statusID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArticleDTO getArticle(int articleID) throws SQLException, ClassNotFoundException {
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, articleID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id")));
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<ArticleDTO> pagingAllArticleAdmin(int index) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where not statusId = 2 order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 20);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public List<ArticleDTO> pagingAllArticleAdminByKeyword(int index, String keyword) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where articelContent like ? and not statusID = 2 order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, (index - 1) * 20);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }
    
    public List<ArticleDTO> pagingAllArticleAdminByKeywordName(int index, String keyword) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where title like ? order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, (index - 1) * 20);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public List<ArticleDTO> pagingAllArticleMember(int index) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where statusID = 3 order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 20);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public List<ArticleDTO> pagingAllArticleMemberByKeyword(int index, String keyword) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where statusID = 3 and articelContent like ? order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, (index - 1) * 20);
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public List<ArticleDTO> pagingAllArticleByStatus(int index, int cid) throws SQLException, ClassNotFoundException {
        List<ArticleDTO> arrayList = new ArrayList<ArticleDTO>();
        try {
            //call dao 
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            conn = new DBUtils().getConnection();
            String sql = "select * from Article where statusID = ? order by postingDate "
                    + "desc offset ? rows fetch next 20 rows only";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, (index - 1) * 20);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleDTO(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("articelContent"),
                        rs.getDate("postingDate"),
                        rs.getString("Description"),
                        accountDAO.findUserByEmail(rs.getString("authorEmail")),
                        rs.getInt("statusID"),
                        commentDAO.GetAllCommentsOfAritcleById(rs.getInt("id"))));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public List<ArticleStatusDTO> GetAllArticleStatus() throws SQLException, ClassNotFoundException {
        List<ArticleStatusDTO> arrayList = new ArrayList<ArticleStatusDTO>();
        try {
            conn = new DBUtils().getConnection();
            String sql = "Select * from ArticleStatus";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                arrayList.add(new ArticleStatusDTO(rs.getInt("statusID"), rs.getString("statusName")));
            }
        } finally {
            closeConnection();
        }
        return arrayList;
    }

    public boolean activeArticle(int articleId) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update Article set statusID = 3 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, articleId);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean activeAllArticle() throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update Article set statusID = 3 where statusID = 1";
            stm = conn.prepareStatement(sql);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteArticle(int articleId) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = new DBUtils().getConnection();
            String sql = "update Article set statusID = 2 where id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, articleId);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArticleDAO articleDAO = new ArticleDAO();
        for (ArticleDTO articleDTO : articleDAO.pagingAllArticleByStatus(1, 3)) {
            System.out.println(articleDTO.toString());
        }
    }
}
