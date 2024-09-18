/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import tuanpq.utils.DBHelper;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDAO implements Serializable{
     public boolean insertItem(String productName, OrderDetailDTO dto) 
            throws ClassNotFoundException, SQLException, NamingException{
        PreparedStatement stm = null;
        Connection con = null;
        boolean result = false;
        
        try {
            // 1. Connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                // 2. Query String
                String sql = "DECLARE @productSKU INT "
                        + "SELECT @productSKU = SKU "
                        + "FROM tblProduct "
                        + "WHERE name = ? "
                        + "DECLARE @orderID varchar(5) "
                        + "SELECT TOP 1 @orderID = id "
                        + "FROM tblOrder "
                        + "ORDER BY id DESC "
                        + "INSERT INTO OrderDetail("
                        + "productID, price, quantity, orderID, total"
                        + ") VALUES("
                        + "@productSKU, ?, ?, @orderID, ?"
                        + ")";
                
                // 3. Statment
                stm = con.prepareStatement(sql);
                stm.setString(1, productName);
                stm.setDouble(2, dto.getPrice());
                stm.setInt(3, dto.getQuantity());
                stm.setDouble(4, dto.getTotal());
                // 4. Excute
                int row = stm.executeUpdate();
                // 5. Process
                if (row > 0) {
                    result = true;
                }
            }
        } finally{
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
}
