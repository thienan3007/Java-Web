/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.users;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class UserFormErrors implements Serializable{
    private String nameError;
    private String phoneError;
    private String addressError;
    private String email;

    public UserFormErrors() {
    }

    public UserFormErrors(String nameError, String phoneError, String addressError, String email) {
        this.nameError = nameError;
        this.phoneError = phoneError;
        this.addressError = addressError;
        this.email = email;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
