/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.account;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class AccountLoginErrors implements Serializable {
    private String emailIsNotExisted;
    private String emailOrPasswordNotMatched;

    public AccountLoginErrors() {
    }

    public AccountLoginErrors(String emailIsNotExisted, String emailOrPasswordNotMatched) {
        this.emailIsNotExisted = emailIsNotExisted;
        this.emailOrPasswordNotMatched = emailOrPasswordNotMatched;
    }

    public String getEmailIsNotExisted() {
        return emailIsNotExisted;
    }

    public void setEmailIsNotExisted(String emailIsNotExisted) {
        this.emailIsNotExisted = emailIsNotExisted;
    }

    public String getEmailOrPasswordNotMatched() {
        return emailOrPasswordNotMatched;
    }

    public void setEmailOrPasswordNotMatched(String emailOrPasswordNotMatched) {
        this.emailOrPasswordNotMatched = emailOrPasswordNotMatched;
    }
     
    
}
