/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.hotel;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class HotelDTO implements Serializable{
    private int hotelId;
    private String name;
    private String address;
    private String phone;
    private String image;
    private int hotelStatusID;

    public HotelDTO() {
    }

    public HotelDTO(int hotelId, String name, String address, String phone, String image, int hotelStatusID) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.image = image;
        this.hotelStatusID = hotelStatusID;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHotelStatusID() {
        return hotelStatusID;
    }

    public void setHotelStatusID(int hotelStatusID) {
        this.hotelStatusID = hotelStatusID;
    }
    
    
}
