/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cakes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author antru
 */
public class CakesDTO implements Serializable {
    private int cakesID;
    private String name;
    private String image;
    private double price;
    private Date createDate;
    private Date expirationDate;
    private int categoryID;
    private int quantity;
    private int statusID;
    private String description;

    public CakesDTO() {
    }

    public CakesDTO(int cakesID, String name, double price, Date createDate, Date expirationDate, int categoryID, int quantity, int statusID, String description, String image) {
        this.cakesID = cakesID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.statusID = statusID;
        this.description = description;
    }
    
    public CakesDTO(String name, double price, Date createDate, Date expirationDate, int categoryID, int quantity, int statusID, String description, String image) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.statusID = statusID;
        this.description = description;
    }

    public int getCakesID() {
        return cakesID;
    }

    public void setCakesID(int cakesID) {
        this.cakesID = cakesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CakesDTO{" + "cakesID=" + cakesID + ", name=" + name + ", image=" + image + ", price=" + price + ", createDate=" + createDate + ", expirationDate=" + expirationDate + ", categoryID=" + categoryID + ", quantity=" + quantity + ", statusID=" + statusID + ", description=" + description + '}';
    }
}
























