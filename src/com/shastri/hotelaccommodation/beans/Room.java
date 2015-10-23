package com.shastri.hotelaccommodation.beans;

public class Room {
    private int roomID = 0;
    private double price  = 0.0d;
    private int capacity = 0;
    private boolean seaView = false;
    private int hotelID = 0;
    
    public Room(int roomID, double price, int capacity, boolean seaView, int hotelID){
        this.roomID = roomID;
        this.price = price;
        this.capacity = capacity;
        this.seaView = seaView;
        this.hotelID = hotelID;
    }

    public int getRoomID(){
        return roomID;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public boolean isSeaView(){
        return seaView;
    }

    public void setSeaView(boolean seaView){
        this.seaView = seaView;
    }

    public int getHotelID(){
        return hotelID;
    }

    public void setHotelID(int hotelID){
        this.hotelID = hotelID;
    }
    
    @Override
    public String toString(){
        return this.roomID + " in hotel " + this.hotelID;
    }
}