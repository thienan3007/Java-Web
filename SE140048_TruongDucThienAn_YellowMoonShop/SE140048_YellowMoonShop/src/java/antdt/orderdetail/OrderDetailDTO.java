/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.orderdetail;

import antdt.cakes.CakesDTO;
import java.io.Serializable;

/**
 *
 * @author antru
 */
public class OrderDetailDTO implements Serializable{
    private int orderDetailID;
    private int orderID;
    private CakesDTO cakeDTO;
    private int quantity;
    private double price;
    private int statusID;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailID, int orderID, CakesDTO cake, int quantity, double price, int statusID) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.cakeDTO = cake;
        this.quantity = quantity;
        this.price = price;
        this.statusID = statusID;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public CakesDTO getCakeDTO() {
        return cakeDTO;
    }

    public void setCakeDTO(CakesDTO cakeDTO) {
        this.cakeDTO = cakeDTO;
    }
    
    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
    
}
























