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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanpq.registration.RegistrationDAO;
import tuanpq.registration.RegistrationDTO;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage";
    private final String SEARCH_PAGE = "searchPage";

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
        String url = LOGIN_PAGE;
        try {
            // 1. lấy toàn bộ cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                // 2. lấy cookie gần nhất
                Cookie recentCookie = cookies[cookies.length-1];
                
                // 3. lấy username và password đối chiếu với DB
                String username = recentCookie.getName();
                String password = recentCookie.getValue();
                // 4. call method DAO
                RegistrationDAO dao = new RegistrationDAO();
                //boolean result = dao.checkLogin(username, password);
                RegistrationDTO result = dao.checklogin(username, password);
                // 5. process
                if (result != null) {
                    url = SEARCH_PAGE;
                }
            }
        }catch(SQLException ex){
            log("STARTUP_SQL_"+ex.getMessage());
        } catch (NamingException ex) {
            log("STARTUP_NAMING_"+ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("STARTUP_ClassNotFound" + ex.getMessage());
        }finally{
            response.sendRedirect(url);
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
