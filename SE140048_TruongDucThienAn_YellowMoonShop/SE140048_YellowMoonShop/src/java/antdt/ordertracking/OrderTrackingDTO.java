/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.ordertracking;

import antdt.cakes.CakesDTO;
import antdt.orderdetail.OrderDetailDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antru
 */
public class OrderTrackingDTO implements Serializable{
    private String username;
    private int orderID;
    private Date orderDate;
    private List<OrderDetailDTO> listOrderDetail;
    private int paymentMethod;
    private boolean paymentStatus;
    private String address;
    private double totalMoney;

    public OrderTrackingDTO() {
    }

    public OrderTrackingDTO(String username, int orderID, Date orderDate, List<OrderDetailDTO> listOrderDetail, int paymentMethod, boolean paymentStatus, String address, double totalMoney) {
        this.username = username;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.listOrderDetail = listOrderDetail;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.address = address;
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetailDTO> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetailDTO> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderTrackingDTO{" + "username=" + username + ", orderID=" + orderID + ", orderDate=" + orderDate + ", listOrderDetail=" + listOrderDetail + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", address=" + address + ", totalMoney=" + totalMoney + '}';
    }
    
    
}
