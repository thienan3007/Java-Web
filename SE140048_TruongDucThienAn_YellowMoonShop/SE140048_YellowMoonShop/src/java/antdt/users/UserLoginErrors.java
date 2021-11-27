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
public class UserLoginErrors {
    private String idNotExisted;
    private String idAndPasswordIsWrong;

    public UserLoginErrors() {
    }

    public UserLoginErrors(String idNotExisted, String idAndPasswordIsWrong) {
        this.idNotExisted = idNotExisted;
        this.idAndPasswordIsWrong = idAndPasswordIsWrong;
    }

    public String getIdNotExisted() {
        return idNotExisted;
    }

    public void setIdNotExisted(String idNotExisted) {
        this.idNotExisted = idNotExisted;
    }

    public String getIdAndPasswordIsWrong() {
        return idAndPasswordIsWrong;
    }

    public void setIdAndPasswordIsWrong(String idAndPasswordIsWrong) {
        this.idAndPasswordIsWrong = idAndPasswordIsWrong;
    }
    
    
}
