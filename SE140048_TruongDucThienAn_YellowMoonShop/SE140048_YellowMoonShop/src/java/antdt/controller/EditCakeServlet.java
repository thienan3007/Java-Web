/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.AddCakeErrors;
import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import static antdt.controller.AddCakesServlet.managerProductPage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antru
 */
@WebServlet(name = "EditCakeServlet", urlPatterns = {"/edit"})
public class EditCakeServlet extends HttpServlet {
    public static final String managerProduct = "ManageProductServlet";
    public static final String errorPage = "loadData?cakesID=";
    
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
        String url = errorPage;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        Date createdDate = Date.valueOf(request.getParameter("createdDate"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        int statusID = Integer.parseInt(request.getParameter("statusID"));
        long milis = System.currentTimeMillis();
        Date today = new Date(milis);
        boolean foundErros = false;
        AddCakeErrors addCakeErrors = new AddCakeErrors();
        url += String.valueOf(id);
        try {
            //validation

            //name errors
            if (name.length() == 0 || name.length() > 50) {
                foundErros = true;
                addCakeErrors.setNameLengthErrors("Cake's name must be from 1 to 50 characters!!!!");
            }

            // image error
            if (image.length() == 0) {
                image = request.getParameter("oleImage");
            }

            //created date errors
            if (createdDate == null) {
                foundErros = true;
                addCakeErrors.setCreatedDateErrors("Created date must not be emptied!!!");
            } else if (createdDate.after(expirationDate)) {
                foundErros = true;
                addCakeErrors.setCreatedDateErrors("Created date must not be after the expiration date!!!!");
            } else if (createdDate.after(today)) {
                foundErros = true;
                addCakeErrors.setCreatedDateErrors("Created date must not be after today!!!!!");
            }

            // expiration date errors
            if (expirationDate == null) {
                foundErros = true;
                addCakeErrors.setExpirationDateErrors("Expiration date must not be emptied!!!");
            }

            if (foundErros) {
                request.setAttribute("AddCakesErrors", addCakeErrors);
            } else {
                // call dao
                CakesDAO cakesDAO = new CakesDAO();
                boolean result = cakesDAO.updateCakesByID(new CakesDTO(id, name, price, createdDate, 
                        expirationDate, categoryID, quantity, statusID, description, image));
                // set attribute
                if (result == true) {
                    url = managerProductPage;
                }
            }
        } catch (SQLException ex) {
            log("EditCakeServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("EditCakeServlet _ ClassNotFound " + ex.getMessage());
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
