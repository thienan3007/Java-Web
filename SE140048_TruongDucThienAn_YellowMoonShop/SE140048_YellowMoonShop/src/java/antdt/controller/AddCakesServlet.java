/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.cakes.AddCakeErrors;
import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import java.io.IOException;
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
@WebServlet(name = "AddCakesServlet", urlPatterns = {"/addCakes"})
public class AddCakesServlet extends HttpServlet {

    public static final String managerProductPage = "ManageProductServlet";
    public static final String addCakepage = "loadAdd";
    
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
        String url = addCakepage;
        // get parameter
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        Date createdDate = Date.valueOf(request.getParameter("createdDate"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        long milis = System.currentTimeMillis();
        Date today = new Date(milis);
        AddCakeErrors addCakeErrors = new AddCakeErrors();
        boolean foundErros = false;
        
        try {
            //validation

            //name errors
            if (name.length() == 0 || name.length() > 50) {
                foundErros = true;
                addCakeErrors.setNameLengthErrors("Cake's name must be from 1 to 50 characters!!!!");
            }

            // image error
            if (image.length() == 0) {
                foundErros = true;
                addCakeErrors.setImageErrors("Cake name must not be emptied!!!!");
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
                boolean result = cakesDAO.addCakes(new CakesDTO(name, price, createdDate, expirationDate, 
                        categoryID, quantity, 1, description, image));
                // set attribute
                if (result == true) {
                    url = managerProductPage;
                }
            }
        } catch (SQLException ex) {
            log("addCakes _ SQl " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("addCakes _ ClassNotFound " + ex.getMessage());
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
