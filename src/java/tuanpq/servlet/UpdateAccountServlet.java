/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanpq.error.AccountUpdateError;
import tuanpq.registration.RegistrationDAO;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
//private final String ERROR_PAGE="errorPage";
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
        //get site map
        Properties siteMap = (Properties) request.getServletContext().getAttribute("SITE_MAP");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String isAdmin = request.getParameter("chkAdmin");
        String searchValue= request.getParameter("lastSearchValue");
        String url = siteMap.getProperty("searchAction") + "?txtSearchValue=" + searchValue;;
        boolean checked = false;
        if(isAdmin!=null){
            checked = true;
        }
        //create error bean
        AccountUpdateError error = new AccountUpdateError();
        boolean isError = false;
        try  {
         if(password.trim().length()<=0){
             error.setPasswordIsEmptyError("password can't not be empty");
             isError = true;
         }//end if password is checked
         if(fullname.trim().length()<=0){
             error.setFullnameIsEmptyError("Full name can't not be empty");
             isError = true;
         }
         if(isError == true){
             request.setAttribute("ERROR", error);
             // ERROR_PK is used to identify which update row contains error
             request.setAttribute("ERROR_PK", username);
         }else{
         RegistrationDAO dao = new RegistrationDAO();
         dao.uppdateAccount(username, password, fullname, checked);
         }// end if error is false
        }catch(SQLException ex){
              log("UpdateAccountServlet_SQL_" + ex.getMessage());
        }catch(ClassNotFoundException ex){
              log("UpdateAccountServlet_ClassNotFound_" + ex.getMessage());
        }catch(NamingException ex){
               log("UpdateAccountServlet_Naming_" + ex.getMessage());      
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
