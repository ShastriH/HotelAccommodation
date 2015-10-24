package com.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri
 */
public class Hotel {
    private int hotelID;
    private String address;
    
    public Hotel(){}
    
    public Hotel(int hotelID, String address){
        this.hotelID = hotelID;
        this.address = address;
    }

    public int getHotelID(){
        return hotelID;
    }

    public void setHotelID(int hotelID){
        this.hotelID = hotelID;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.hotelID);
    }
}
