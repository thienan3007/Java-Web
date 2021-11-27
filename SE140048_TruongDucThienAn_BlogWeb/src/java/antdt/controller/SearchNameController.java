/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.account.AccountDTO;
import antdt.article.ArticleDAO;
import antdt.article.ArticleDTO;
import antdt.articlestatus.ArticleStatusDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antru
 */
@WebServlet(name = "SearchNameController", urlPatterns = {"/SearchNameController"})
public class SearchNameController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        String indexPage = request.getParameter("index");
        try {
            if (indexPage == null) {
                indexPage = "1";
            }
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
            if (accountDTO != null) {
                if (accountDTO.getRole().getId() == 1) {
                    ArticleDAO articleDAO = new ArticleDAO();
                    int count = articleDAO.getTotalArticleMemberByKeywordName(keyword);
                    int endPage = count / 20;
                    if (count % 20 != 0) {
                        endPage++;
                    }
                    List<ArticleDTO> articleList = articleDAO.pagingAllArticleAdminByKeywordName(Integer.parseInt(indexPage), keyword);
                    List<ArticleStatusDTO> statusList = articleDAO.GetAllArticleStatus();

                    //set attribute
                    request.setAttribute("endPage", endPage);
                    request.setAttribute("tag", Integer.parseInt(indexPage));
                    request.setAttribute("articleAdmin", articleList);
                    request.setAttribute("statusList", statusList);
                    request.setAttribute("keyword", keyword);
                    request.setAttribute("searchName", "searchName");

                    request.getRequestDispatcher("homePage").forward(request, response);
                }
            }
        } catch (SQLException e) {
            log("SearchNameController _ SQL " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchNameController _ ClassNotFound " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
