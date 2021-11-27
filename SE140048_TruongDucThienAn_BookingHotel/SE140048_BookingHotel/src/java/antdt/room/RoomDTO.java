/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.room;

import antdt.hotel.HotelDTO;
import antdt.roomtype.RoomTypeDTO;
import java.io.Serializable;

/**
 *
 * @author antru
 */
public class RoomDTO implements Serializable{
    private int roomID;
    private String name;
    private HotelDTO hotel;
    private int roomNumber;
    private double price;
    private RoomTypeDTO roomType;
    private String description;
    private String image;
    private int roomStatusID;

    public RoomDTO() {
    }

    public RoomDTO(int roomID, String name, HotelDTO hotel, int roomNumber, double price, RoomTypeDTO roomType, String description, String image, int roomStatusID) {
        this.roomID = roomID;
        this.name = name;
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.description = description;
        this.image = image;
        this.roomStatusID = roomStatusID;
    }

    

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
    }

    

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomStatusID() {
        return roomStatusID;
    }

    public void setRoomStatusID(int roomStatusID) {
        this.roomStatusID = roomStatusID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }        

    @Override
    public String toString() {
        return "RoomDTO{" + "roomID=" + roomID + ", name=" + name + ", hotel=" + hotel + ", roomNumber=" + roomNumber + ", price=" + price + ", roomType=" + roomType + ", description=" + description + ", image=" + image + ", roomStatusID=" + roomStatusID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.roomID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoomDTO other = (RoomDTO) obj;
        if (this.roomID != other.roomID) {
            return false;
        }
        return true;
    }
    
    

}
