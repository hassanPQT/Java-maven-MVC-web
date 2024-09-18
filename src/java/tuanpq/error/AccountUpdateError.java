/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.error;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class AccountUpdateError implements Serializable{
    private String passwordIsEmptyError;
    private String fullnameIsEmptyError;

    public AccountUpdateError() {
    }

    /**
     * @return the passwordIsEmptyError
     */
    public String getPasswordIsEmptyError() {
        return passwordIsEmptyError;
    }

    /**
     * @param passwordIsEmptyError the passwordIsEmptyError to set
     */
    public void setPasswordIsEmptyError(String passwordIsEmptyError) {
        this.passwordIsEmptyError = passwordIsEmptyError;
    }

    /**
     * @return the fullnameIsEmptyError
     */
    public String getFullnameIsEmptyError() {
        return fullnameIsEmptyError;
    }

    /**
     * @param fullnameIsEmptyError the fullnameIsEmptyError to set
     */
    public void setFullnameIsEmptyError(String fullnameIsEmptyError) {
        this.fullnameIsEmptyError = fullnameIsEmptyError;
    }
    
}
