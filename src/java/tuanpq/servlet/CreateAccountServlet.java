/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanpq.error.CreateAccountError;
import tuanpq.registration.RegistrationDAO;
import tuanpq.registration.RegistrationDTO;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
private final String ERROR_PAGE = "registerPage";
private final String LOGIN_PAGE = "loginPage";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
        boolean foundErr = false;
        CreateAccountError error = new CreateAccountError();
        String url = ERROR_PAGE;
        try  {
           if(username.trim().length()<6 || username.trim().length()>12){
               foundErr = true;
               error.setUsernameLengthErr("username must 6-12 characters");
           }
           if(password.trim().length()<6 || password.trim().length()>20){
               foundErr = true;
               error.setPasswordLengthErr("password must 6-20 characters");
           }else if(!confirm.trim().equals(password.trim())){
               foundErr = true;
               error.setConfirmNotMatched("password confirm doesn't matched password");
           }
           if(fullname.trim().length()<2 || fullname.trim().length()>50){
               foundErr = true;
               error.setFullNameLengthErr("fullname must 2-50 characters");
           }
           if(foundErr){
               request.setAttribute("CREATE_ERROR", error);
           }else{
               RegistrationDAO dao = new RegistrationDAO();
               RegistrationDTO dto = new RegistrationDTO(username,password,fullname,false);
               boolean result = dao.createAccount(dto);
               if(result){
                   url = LOGIN_PAGE;
               }
           }
        }catch(SQLException ex){
                error.setDuplicatedUsername("Username " + username + " already exist");
                request.setAttribute("CREATE_ERROR",error);
                log("CreateAccountServlet_SQL_" + ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("CreateAccountServle_ClassNotFound_" + ex.getMessage());
        }catch(NamingException ex){
            log("CreateAccountServlet_Naming_" + ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
