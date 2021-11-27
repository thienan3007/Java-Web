/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import antdt.cakestatus.CakeStatusDAO;
import antdt.cakestatus.CakeStatusDTO;
import antdt.category.CategoryDAO;
import antdt.category.CategoryDTO;
import static antdt.controller.DetailServlet.detailPage;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoadCakeServlet", urlPatterns = {"/loadData"})
public class LoadCakeServlet extends HttpServlet {
    public static final String editPage = "Edit.jsp";
    
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
        int id = Integer.parseInt(request.getParameter("cakesID"));

        try {
            // call DAO
            CakesDAO cakesDAO = new CakesDAO();
            CakesDTO cakesDTO = cakesDAO.loadCakesDataByid(id);
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryList = categoryDAO.getAllCategory();
            CakeStatusDAO cakeStatusDAO = new CakeStatusDAO();
            List<CakeStatusDTO> list = cakeStatusDAO.getAllCakeStatus();

            // set request attribute
            request.setAttribute("cake", cakesDTO);
            request.setAttribute("cakeId", id);
            request.setAttribute("listCategory", categoryList);
            request.setAttribute("cakeStatusList", list);
        } catch (SQLException ex) {
            log("detail _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("detail _ ClassNotFound " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(editPage).forward(request, response);
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
