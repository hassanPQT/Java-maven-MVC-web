/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuanpq.cart.CartBean;
import tuanpq.orderdetail.OrderDetailDAO;
import tuanpq.tblOrder.OrderDTO;
import tuanpq.orderdetail.OrderDetailDTO;
import tuanpq.registration.RegistrationDAO;
import tuanpq.tblOrder.TblOrderDAO;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
private final String ORDER_SUCCESS = "orderSuccess";
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
          PrintWriter out = response.getWriter();
        
        // 1. Get info
        String name = request.getParameter("txtCustomerName");
        String address = request.getParameter("txtAddress");
        String email = request.getParameter("txtEmail");
        String url = ORDER_SUCCESS;
        try{
            // 2. insert customer info
            TblOrderDAO dao = new TblOrderDAO();
            OrderDTO dto = new OrderDTO("", "", name, address, email, 0);
            //insert order
            dao.insertOrder(dto);
            OrderDTO result = dao.getOrderByCustomerAndEmail(name, email);
            if (result != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("ORDER", result);
            }    
            
            // 3.insert product
            // take cart place
            HttpSession session = request.getSession();
            if (session != null) {
                // take cart
                CartBean cart = (CartBean)session.getAttribute("CART");
                if (cart != null) {
                    // take ngăn chứa
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        OrderDetailDAO OrderDetail = new OrderDetailDAO();
                        for (Map.Entry<String, Integer> entry : items.entrySet()){
                            String productName = entry.getKey();
                            int quantity = entry.getValue();
                            
                            OrderDetailDTO dtoOrderDetail = new OrderDetailDTO(0, quantity, 0);
                            OrderDetail.insertItem(productName, dtoOrderDetail);
                        }
                        session.setAttribute("CART", cart);
                    }
                } 
            }
        } catch (NamingException ex) {
            log("INSERT_NAMING_"+ex.getMessage());
        } catch (SQLException ex) {
            log("INSERT_SQL_"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("INSERT_ClassNotFound_"+ex.getMessage());
        }finally{
            response.sendRedirect(url);
            out.close();
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
