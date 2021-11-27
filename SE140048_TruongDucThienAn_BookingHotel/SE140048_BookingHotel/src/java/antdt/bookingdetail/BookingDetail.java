package antdt.bookingdetail;


import antdt.booking.BookingDTO;
import antdt.room.RoomDTO;
import antdt.roomtype.RoomTypeDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antru
 */
public class BookingDetail implements Serializable{
    private int id;
    private int bookingID;
    private double price;
    private RoomDTO room;
    private RoomTypeDTO roomType;
    private boolean status;
    private Date dateFrom;
    private Date dateTo;
    private String dateFromString;
    private String dateToString;
    private int quantity;

    public BookingDetail() {
    }

    public BookingDetail(int id, int bookingID, double price, RoomDTO room, RoomTypeDTO roomType, boolean status, Date dateFrom, Date dateTo, int quantity) {
        this.id = id;
        this.bookingID = bookingID;
        this.price = price;
        this.room = room;
        this.roomType = roomType;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.quantity = quantity;
    }

    public String getDateFromString() {
        return dateFromString;
    }

    public void setDateFromString(String dateFromString) {
        this.dateFromString = dateFromString;
    }

    public String getDateToString() {
        return dateToString;
    }

    public void setDateToString(String dateToString) {
        this.dateToString = dateToString;
    }
    
    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
