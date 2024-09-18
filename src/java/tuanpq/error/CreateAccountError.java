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
public class CreateAccountError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatched;
    private String duplicatedUsername;

    public CreateAccountError() {
    }

    public CreateAccountError(String usernameLengthErr, String passwordLengthErr, String fullNameLengthErr, String confirmNotMatched, String duplicatedUsername) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.confirmNotMatched = confirmNotMatched;
        this.duplicatedUsername = duplicatedUsername;
    }

    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the duplicatedUsername
     */
    public String getDuplicatedUsername() {
        return duplicatedUsername;
    }

    /**
     * @param duplicatedUsername the duplicatedUsername to set
     */
    public void setDuplicatedUsername(String duplicatedUsername) {
        this.duplicatedUsername = duplicatedUsername;
    }
    
}
