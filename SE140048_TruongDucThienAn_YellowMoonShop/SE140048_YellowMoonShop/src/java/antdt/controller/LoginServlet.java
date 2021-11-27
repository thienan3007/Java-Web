/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.users.UserLoginErrors;
import antdt.users.UsersDAO;
import antdt.users.UsersDTO;
import antdt.users.UsersSignUpErrors;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antru
 */
public class LoginServlet extends HttpServlet {

    public static final String loginPage = "Login.jsp";
    public static final String errorPage = "invalid.html";
    public static final String managerProduct = "ManageProductServlet";
    public static final String homePage = "HomeServlet";

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
        String url = loginPage;
        String button = request.getParameter("btnAction");
        try {
            if (button.equals("Login")) {
                boolean foundErrors = false;

                //call dao
                UsersDAO dao = new UsersDAO();

                //errors DTO
                UserLoginErrors userLoginErrors = new UserLoginErrors();

                //get parameter
                int userID = Integer.parseInt(request.getParameter("user"));
                String password = request.getParameter("pass");

                //validation
                if (!dao.findUser(userID)) {
                    foundErrors = true;
                    userLoginErrors.setIdNotExisted("ID is not existed!!!");
                }
                if (dao.checkLogin(userID, password) == null) {
                    foundErrors = true;
                    userLoginErrors.setIdAndPasswordIsWrong("ID and password was wrong!!!");
                }

                //if has errors
                if (foundErrors) {
                    request.setAttribute("LOGIN_ERROR", userLoginErrors);
                } else {
                    //if checklogin is successful
                    UsersDTO account = dao.checkLogin(userID, password);
                    HttpSession session = request.getSession();
                    url = errorPage;
                    if (account != null) {
                        url = homePage;
                        session.setAttribute("account", account);
                    }
                }
            } else if (button.equals("signup")) {

                boolean foundErrors = false;

                //call dao
                UsersDAO dao = new UsersDAO();

                //errors DTO
                UsersSignUpErrors usersSignUpErrors = new UsersSignUpErrors();

                //get parameter
                int userID = Integer.parseInt(request.getParameter("user"));
                String password = request.getParameter("pass");
                String repass = request.getParameter("repass");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String username = request.getParameter("username");
                //validation
                if (!Pattern.compile("^[0-9]+$.*").matcher(String.valueOf(userID)).find()) {
                    foundErrors = true;
                    usersSignUpErrors.setIdErrors("User ID must be number!!!!");
                } else {
                    if (dao.findUser(userID)) {
                        foundErrors = true;
                        usersSignUpErrors.setIdIsExisted("ID is existed!!!");
                    }
                }
                
                if (Pattern.compile("^[0-9]+$.*").matcher(String.valueOf(username)).find()) {
                    foundErrors = true;
                    usersSignUpErrors.setUsernameErrors("Name must not have number!!!!");
                } else if (username.length() > 100) {
                    foundErrors = true;
                    usersSignUpErrors.setUsernameLengthErrors("Name length must not fewer than 100 characters!!!");
                }

                if (!password.equals(repass)) {
                    foundErrors = true;
                    usersSignUpErrors.setPasswordAndConfirmPasswordIsNotMatched("Password and Confirm password was not matched!!!");
                }
                if (phone.length() > 20) {
                    foundErrors = true;
                    usersSignUpErrors.setPhoneLengthErrors("Phone must not be longer than 20 characters!!!");
                } else if (!Pattern.matches("^[0-9]+$", phone)) {
                    foundErrors = true;
                    usersSignUpErrors.setPhoneErrors("Could not enter characters for phone numbers!!!!");
                }

                //if has errors
                if (foundErrors) {
                    request.setAttribute("SIGNUP_ERROR", usersSignUpErrors);
                } else {
                    //if checklogin is successful
                    UsersDTO usersDTO = new UsersDTO(userID, username, password, phone, address, new Date(), 2, false);
                    HttpSession session = request.getSession();
                    url = errorPage;
                    if (dao.insertNewAccountUser(usersDTO)) {
                        url = homePage;
                        session.setAttribute("account", usersDTO);
                    }
                }
            }

        } catch (SQLException ex) {
            log("LoginServlet_SQl " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoginServlet_ClassNotFound " + ex.getMessage());
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
