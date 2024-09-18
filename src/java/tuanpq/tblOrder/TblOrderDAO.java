/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.tblOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import tuanpq.utils.DBHelper;

/**
 *
 * @author LENOVO
 */
public class TblOrderDAO implements Serializable {
     private List<OrderDTO> orderDetail;

    public List<OrderDTO> getOrderDetail() {
        return orderDetail;
    }

    public boolean insertOrder(OrderDTO dto) throws NamingException, SQLException{
        
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try{
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrder("
                        + "date, customer, address, email, total"
                        + ") VALUES("
                        + "GETDATE(), ?, ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getCustomer());
                stm.setString(2, dto.getAddress());
                stm.setString(3, dto.getEmail());
                stm.setDouble(4, dto.getTotal());
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    result = true;
                }
            }
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    public OrderDTO getOrderByCustomerAndEmail(String customer, String email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO dto = null;
        
        try{
            con = DBHelper.getConnection();
            
            String sql = "SELECT TOP 1 id, date, address, total "
                    + "FROM tblOrder "
                    + "WHERE customer = ? "
                    + "AND email = ? "
                    + "ORDER BY id DESC";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, customer);
            stm.setString(2, email);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                // get from db
                String id = rs.getString("id");
                String date = rs.getString("date");
                String address = rs.getString("address");
                double total = rs.getDouble("total");
                // set into dto
                dto = new OrderDTO(id, date, customer, address, email, total);
            }
            
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    
}
