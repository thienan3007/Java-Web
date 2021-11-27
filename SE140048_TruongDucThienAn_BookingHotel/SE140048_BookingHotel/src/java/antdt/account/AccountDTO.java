/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.account;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author antru
 */
public class AccountDTO implements Serializable{
    private String email;
    private String phone;
    private String name;
    private String address;
    private Date createDate;
    private String password;
    private int accountRoleID;

    public AccountDTO() {
    }

    public AccountDTO(String email, String phone, String name, String address, Date createDate, String password, int accountRoleID) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.createDate = createDate;
        this.password = password;
        this.accountRoleID = accountRoleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getAccountRoleID() {
        return accountRoleID;
    }

    public void setAccountRoleID(int accountRoleID) {
        this.accountRoleID = accountRoleID;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "email=" + email + ", phone=" + phone + ", name=" + name + ", address=" + address + ", createDate=" + createDate + ", password=" + password + ", accountRoleID=" + accountRoleID + '}';
    }
    
    
}
