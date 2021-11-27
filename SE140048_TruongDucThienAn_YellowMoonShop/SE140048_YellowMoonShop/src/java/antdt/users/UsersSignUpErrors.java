/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.users;

/**
 *
 * @author antru
 */
public class UsersSignUpErrors {
    private String idIsExisted;
    private String passwordAndConfirmPasswordIsNotMatched;
    private String phoneErrors;
    private String phoneLengthErrors;
    private String addressErrors;
    private String idErrors;
    private String usernameLengthErrors;
    private String usernameErrors;

    public UsersSignUpErrors() {
    }

    public UsersSignUpErrors(String idIsExisted, String passwordAndConfirmPasswordIsNotMatched, String phoneErrors, String phoneLengthErrors, String addressErrors, String idErrors, String usernameLengthErrors, String usernameErrors) {
        this.idIsExisted = idIsExisted;
        this.passwordAndConfirmPasswordIsNotMatched = passwordAndConfirmPasswordIsNotMatched;
        this.phoneErrors = phoneErrors;
        this.phoneLengthErrors = phoneLengthErrors;
        this.addressErrors = addressErrors;
        this.idErrors = idErrors;
        this.usernameLengthErrors = usernameLengthErrors;
        this.usernameErrors = usernameErrors;
    }

    public String getUsernameLengthErrors() {
        return usernameLengthErrors;
    }

    public void setUsernameLengthErrors(String usernameLengthErrors) {
        this.usernameLengthErrors = usernameLengthErrors;
    }

    public String getUsernameErrors() {
        return usernameErrors;
    }

    public void setUsernameErrors(String usernameErrors) {
        this.usernameErrors = usernameErrors;
    }

    

    public String getIdErrors() {
        return idErrors;
    }

    public void setIdErrors(String idErrors) {
        this.idErrors = idErrors;
    }

    public String getPhoneErrors() {
        return phoneErrors;
    }

    public void setPhoneErrors(String phoneErrors) {
        this.phoneErrors = phoneErrors;
    }

    public String getPhoneLengthErrors() {
        return phoneLengthErrors;
    }

    public void setPhoneLengthErrors(String phoneLengthErrors) {
        this.phoneLengthErrors = phoneLengthErrors;
    }

    public String getAddressErrors() {
        return addressErrors;
    }

    public void setAddressErrors(String addressErrors) {
        this.addressErrors = addressErrors;
    }

    public String getIdIsExisted() {
        return idIsExisted;
    }

    public void setIdIsExisted(String idIsExisted) {
        this.idIsExisted = idIsExisted;
    }

    public String getPasswordAndConfirmPasswordIsNotMatched() {
        return passwordAndConfirmPasswordIsNotMatched;
    }

    public void setPasswordAndConfirmPasswordIsNotMatched(String passwordAndConfirmPasswordIsNotMatched) {
        this.passwordAndConfirmPasswordIsNotMatched = passwordAndConfirmPasswordIsNotMatched;
    }
}
