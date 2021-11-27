/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.booking;

import antdt.bookingdetail.BookingDetail;
import antdt.hotel.HotelDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author antru
 */
public class BookingDTO implements Serializable{
    private int id;
    private HotelDTO hotelDTO;
    private String accountEmail;
    private List<BookingDetail> listBookingDetail;
    private int roomAmount;
    private double totalPrice;
    private Date bookingDate;
    private String bookingDateString;
    private int bookingStatusID;

    public BookingDTO() {
    }

    public BookingDTO(int id, HotelDTO hotelDTO, String accountEmail, List<BookingDetail> listBookingDetail, int roomAmount, double totalPrice, Date bookingDate, int bookingStatusID, String bookingDateString) {
        this.id = id;
        this.hotelDTO = hotelDTO;
        this.accountEmail = accountEmail;
        this.listBookingDetail = listBookingDetail;
        this.roomAmount = roomAmount;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
        this.bookingStatusID = bookingStatusID;
        this.bookingDateString = bookingDateString;
    }

    public String getBookingDateString() {
        return bookingDateString;
    }

    public void setBookingDateString(String bookingDateString) {
        this.bookingDateString = bookingDateString;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public List<BookingDetail> getListBookingDetail() {
        return listBookingDetail;
    }

    public void setListBookingDetail(List<BookingDetail> listBookingDetail) {
        this.listBookingDetail = listBookingDetail;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBookingStatusID() {
        return bookingStatusID;
    }

    public void setBookingStatusID(int bookingStatusID) {
        this.bookingStatusID = bookingStatusID;
    }

    @Override
    public String toString() {
        return "BookingDTO{" + "id=" + id + ", hotelDTO=" + hotelDTO + ", accountEmail=" + accountEmail + ", listBookingDetail=" + listBookingDetail + ", roomAmount=" + roomAmount + ", totalPrice=" + totalPrice + ", bookingDate=" + bookingDate + ", bookingDateString=" + bookingDateString + ", bookingStatusID=" + bookingStatusID + '}';
    }
    
    
}
