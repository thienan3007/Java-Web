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
@WebServlet(name = "HomeLoadController", urlPatterns = {"/HomeLoadController"})
public class HomeLoadController extends HttpServlet {

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
        String indexPage = request.getParameter("index");
        try {
            if (indexPage == null) {
                indexPage = "1";
            }
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("account");
            if (account == null) {
                loadHomeForMember(request, response, Integer.parseInt(indexPage));
            } else {
                if (account.getRole().getId() == 1) {
                    loadHomeForAdmin(request, response, Integer.parseInt(indexPage));
                } else {
                    loadHomeForMember(request, response, Integer.parseInt(indexPage));
                }
            }
        } catch (SQLException ex) {
            log("HomeLoadController _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("HomeLoadController _ ClassNotFound " + ex.getMessage());
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

    private void loadHomeForAdmin(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArticleDAO articleDAO = new ArticleDAO();
        int count = articleDAO.getTotalArticleAdmin();
        int endPage = count / 20;
        if (count % 20 != 0) {
            endPage++;
        }
        List<ArticleDTO> articleList = articleDAO.pagingAllArticleAdmin(page);
        List<ArticleStatusDTO> statusList = articleDAO.GetAllArticleStatus();

        //set attribute
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("articleAdmin", articleList);
        request.setAttribute("statusList", statusList);
        
        request.getRequestDispatcher("homePage").forward(request, response);
    }
    
    private void loadHomeForMember(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArticleDAO articleDAO = new ArticleDAO();
        int count = articleDAO.getTotalArticleMember();
        int endPage = count / 20;
        if (count % 20 != 0) {
            endPage++;
        }
        List<ArticleDTO> articleList = articleDAO.pagingAllArticleMember(page);
        List<ArticleStatusDTO> statusList = articleDAO.GetAllArticleStatus();

        //set attribute
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("articleMember", articleList);
        request.setAttribute("statusList", statusList);
        
        request.getRequestDispatcher("homePage").forward(request, response);
    }
}
