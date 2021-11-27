/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.orders;

import antdt.orderdetail.OrderDetailDTO;
import antdt.users.UsersDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antru
 */
public class OrdersDTO implements Serializable{
    private int orderID;
    private UsersDTO customer;
    private String address;
    private double totalMoney;
    private Date orderDate;
    private List<OrderDetailDTO> orderDetail;
    private int statusID;
    private String email;
    private boolean paymentStatus;
    private int paymentMethodID;
    private String phone;
    private String name;
    

    public OrdersDTO() {
    }

    public OrdersDTO(int orderID, UsersDTO customer, String address, double totalMoney, Date orderDate, List<OrderDetailDTO> orderDetail, int statusID, String email, boolean paymentStatus, int paymentMethodID, String phone, String name) {
        this.orderID = orderID;
        this.customer = customer;
        this.address = address;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.orderDetail = orderDetail;
        this.statusID = statusID;
        this.email = email;
        this.paymentStatus = paymentStatus;
        this.paymentMethodID = paymentMethodID;
        this.phone = phone;
        this.name = name;
    }

    

    public UsersDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UsersDTO customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDTO> orders) {
        this.orderDetail = orders;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public UsersDTO getUserID() {
        return customer;
    }

    public void setUserID(UsersDTO customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
        
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
    
    
}























