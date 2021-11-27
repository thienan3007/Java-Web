/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import antdt.category.CategoryDAO;
import antdt.category.CategoryDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antru
 */
public class HomeServlet extends HttpServlet {

    public static final String homePage = "Home.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = homePage;
        String indexPage = request.getParameter("index");
        try {
            //index paging
            if (indexPage == null) {
                indexPage = "1";
            }
            
            //get data from DAO
            CakesDAO cakesDao = new CakesDAO();
            int count = cakesDao.getTotalCakeHomePage();
            int endPage = count/6;
            if (count % 6 != 0) {
                endPage++;
            }
            List<CakesDTO> arrayList = cakesDao.pagingAllCakes(Integer.parseInt(indexPage));
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryList = categoryDAO.getAllCategory();

            //set data attribute
            request.setAttribute("endPage", endPage);
            request.setAttribute("cakeA", arrayList);
            request.setAttribute("tag", indexPage);
            request.setAttribute("listCategory", categoryList);

        } catch (SQLException ex) {
            log("HomeServlet _ SQl " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("HomeServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
