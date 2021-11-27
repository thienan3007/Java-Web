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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antru
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    public static final String homePage = "Home.jsp";
    public static final int amount = 6;
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
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String index = request.getParameter("index");
        try {
            //index page
            if (index == null) {
                index = "1";
            }
            //call dao
            CakesDAO cakesDAO = new CakesDAO();
            int count  = cakesDAO.getTotalCakeHomePageSearchName(name);
            int endPage = count/amount;
            if(count % amount != 0) {
                endPage++;
            }
            
            List<CakesDTO> arrayList = cakesDAO.pagingAllCakesSearchName(Integer.parseInt(index), name);
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryList = categoryDAO.getAllCategory();
            
            //set attribute
            request.setAttribute("cakeN", arrayList);
            request.setAttribute("endPage", endPage);
            request.setAttribute("tag", index);
            request.setAttribute("name", name);
            request.setAttribute("listCategory", categoryList);

        } catch (SQLException ex) {
            log("SearchServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchServlet _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(homePage).forward(request, response);
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
