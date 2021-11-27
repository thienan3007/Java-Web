/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.account;

import antdt.accountstatus.AccountStatusDTO;
import antdt.role.RoleDTO;
import java.io.Serializable;

/**
 *
 * @author antru
 */
public class AccountDTO implements Serializable{
    private  String email;
    private  String name;
    private String password;
    private AccountStatusDTO status;
    private RoleDTO role;

    public AccountDTO(String email, String name, String password, AccountStatusDTO status, RoleDTO role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    

    public AccountDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatusDTO getStatus() {
        return status;
    }

    public void setStatus(AccountStatusDTO status) {
        this.status = status;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "email=" + email + ", name=" + name + ", password=" + password + ", status=" + status + ", role=" + role + '}';
    }
    

}
