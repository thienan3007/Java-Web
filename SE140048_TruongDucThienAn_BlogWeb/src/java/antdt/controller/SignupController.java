/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.controller;

import antdt.account.AccountDAO;
import antdt.account.AccountDTO;
import antdt.account.AccountSignupErrors;
import antdt.accountstatus.AccountStatusDAO;
import antdt.accountstatus.AccountStatusDTO;
import antdt.role.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author antru
 */
@WebServlet(name = "SignupController", urlPatterns = {"/SignupController"})
public class SignupController extends HttpServlet {

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
        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        try {
            boolean foundErrors = false;

            //call dao
            AccountDAO accountDAO = new AccountDAO();

            //errors DTO
            AccountSignupErrors accountSignupErrors = new AccountSignupErrors();

            //get parameter
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String username = request.getParameter("username");
            //validation
            if (!Pattern.compile("^(.+)@(.+)$").matcher(email).find()) {
                foundErrors = true;
                accountSignupErrors.setIdErrors("User Email must be right!!!!");
            } else {
                if (accountDAO.findUser(email)) {
                    foundErrors = true;
                    accountSignupErrors.setIdIsExisted("Email is existed!!!");
                }
            }

            if (Pattern.compile("^[0-9]+$.*").matcher(String.valueOf(username)).find()) {
                foundErrors = true;
                accountSignupErrors.setUsernameErrors("Name must not have number!!!!");
            } else if (username.length() > 50) {
                foundErrors = true;
                accountSignupErrors.setUsernameLengthErrors("Name length must not fewer than 50 characters!!!");
            }

            if (!password.equals(repass)) {
                foundErrors = true;
                accountSignupErrors.setPasswordAndConfirmPasswordIsNotMatched("Password and Confirm password was not matched!!!");
            }
//            if (phone.length() > 20) {
//                foundErrors = true;
//                accountSignupErrors.setPhoneLengthErrors("Phone must not be longer than 20 characters!!!");
//            } else if (!Pattern.matches("^[0-9]+$", phone)) {
//                foundErrors = true;
//                accountSignupErrors.setPhoneErrors("Could not enter characters for phone numbers!!!!");
//            }

            //if has errors
            if (foundErrors) {
                request.setAttribute("SIGNUP_ERROR", accountSignupErrors);
                request.getRequestDispatcher("signupPage").forward(request, response);
            } else {
                //if checklogin is successful
                password = BCrypt.hashpw(password, BCrypt.gensalt(12));
                RoleDAO roleDAO = new RoleDAO();
                AccountStatusDAO accountStatusDAO = new AccountStatusDAO();
                AccountDTO accountDTO = new AccountDTO(email, username, password, accountStatusDAO.
                        getAccountStatus(1), roleDAO.getRole(2));
                HttpSession session = request.getSession();
                if (accountDAO.InsertAccount(accountDTO)) {

                    session.setAttribute("account", accountDTO);
                    request.getRequestDispatcher("loginPage").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            log("SignupServlet _ SQl " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SignupServlet _ ClassNotFoundException " + ex.getMessage());
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
