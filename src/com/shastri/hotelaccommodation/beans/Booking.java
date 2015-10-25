package com.shastri.hotelaccommodation.beans;

import java.sql.Date;

public class Booking {
    private int bookingID = 0;
    private int roomID = 0;
    private Date arrival = null;
    private Date departure  = null;
    
    public Booking(){}
    
    public Booking(int bookingID, int roomID, Date arrival, Date departure){
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.arrival = arrival;
        this.departure = departure;
    }

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    public int getRoomID(){
        return roomID;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public Date getArrival(){
        return arrival;
    }

    public void setArrival(Date arrival){
        this.arrival = arrival;
    }

    public Date getDeparture(){
        return departure;
    }

    public void setDeparture(Date departure){
        this.departure = departure;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.bookingID);
    }
}
