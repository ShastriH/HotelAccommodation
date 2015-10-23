package com.shastri.hotelaccommodation.beans;

public class GuestBooking {
    private int guestBookingID = 0;
    private int bookingID = 0;
    private int guestID = 0;

    public int getGuestBookingID(){
        return guestBookingID;
    }

    public void setGuestBookingID(int guestBookingID){
        this.guestBookingID = guestBookingID;
    }

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    public int getGuestID(){
        return guestID;
    }

    public void setGuestID(int guestID){
        this.guestID = guestID;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.guestBookingID);
    }
}
