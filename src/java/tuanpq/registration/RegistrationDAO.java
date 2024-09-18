/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.registration;

import tuanpq.orderdetail.OrderDetailDTO;
import tuanpq.tblOrder.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tuanpq.utils.DBHelper;

/**
 *
 * @author LENOVO
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checklogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1 connect db
            con = DBHelper.getConnection();
            //2 create sql string
            String sql = "SELECT lastname,isAdmin "
                    + "FROM registration "
                    + "WHERE username = ? "
                    + "AND Password = ? ";
            //3 create statement object
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            //4 excute query
            rs = stm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("lastname");
                boolean role = rs.getBoolean("isAdmin");
                result = new RegistrationDTO(username, "", fullname, role);
            }
        } finally {
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
        return result;
    }
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect database
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql String
                // ko sai * de chet nguoi
                String sql = "SELECT username,Password,lastname,isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname Like ?";
                //3. create statement objec

                stm = con.prepareStatement(sql);
                // set tham số % dại diện nhiều kí tự
                stm.setString(1, "%" + searchValue + "%");

                //4. excute query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    //.map
                    //get data from result sec
                    String username = rs.getString("username");
                    String password = rs.getString("Password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //set data to dto properties
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, fullname, role);
                    if (this.accounts == null) {
                        //arr ctrl space 3
                        this.accounts = new ArrayList<>();
                    } //end account are not exist 
                    this.accounts.add(dto);
                }
            }//prccess when connection is exist

        } finally {
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

    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. connect database
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql String
                String sql = "Delete From Registration "
                        + "Where username = ? ";
                //3. create statement objec
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. excute query
                int affectedRows = stm.executeUpdate();
                //5. process result
                if (affectedRows > 0) {
                    result = true;
                }
            }

        } finally {
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

        return result;
    }

    public boolean uppdateAccount(String username, String password, String fullname, boolean isAdmin) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            // 1. Connect to DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql String
                String sql = "Update Registration "
                        + "SET Password = ? , lastname = ? , isAdmin = ? "
                        + "Where username = ?";
                //3. create statement objec
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullname);
                stm.setBoolean(3, isAdmin);
                stm.setString(4, username);
                //4. excute query
                int affectedRows = stm.executeUpdate();
                //5. process result
                if (affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
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
        return result;

    }

    public boolean createAccount(RegistrationDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "INSERT INTO dbo.Registration ("
                        + "username, Password, lastname, isAdmin"
                        + ") VALUES("
                        + "?, ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }
            }

        } finally {
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
