/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.roomtype;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class RoomTypeDTO implements Serializable{
    private int id;
    private String name;
    private double price;
    private int roomAmount;
    private String description;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(int id, String name, double price, int roomAmount, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.roomAmount = roomAmount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" + "id=" + id + ", name=" + name + ", price=" + price + ", roomAmount=" + roomAmount + ", description=" + description + '}';
    }
    
    
}
