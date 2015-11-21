package me.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri
 */
public class Room {
    private int roomID = 0;
    private double price  = 0.0d;
    private int capacity = 0;
    private boolean seaView = false;
    private int hotelID = 0;

    /**
     * No arguments constructor.
     */
    public Room(){}

    /**
     * This is the constructor used to create a Room object using data provided by
     * the user which is to be inserted as a new row into the room table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param price         The price of the room (in euros)
     * @param capacity      The number of people the room is designated to support
     * @param seaView       The boolean indicating whether the room has a sea view
     * @param hotelID       The ID of the hotel in which the room is located
     */
    public Room(double price, int capacity, boolean seaView, int hotelID){
        this.price = price;
        this.capacity = capacity;
        this.seaView = seaView;
        this.hotelID = hotelID;
    }

    /**
     * This is the constructor used to create a complete Room object.
     * @param roomID        The ID of the room record
     * @param price         The cost of the room per day (in Euros)
     * @param capacity      The number of people the room is designated to support
     * @param seaView       The boolean indicating whether the room has a sea view
     * @param hotelID       The ID of the hotel in which the room is located
     */
    public Room(int roomID, double price, int capacity, boolean seaView, int hotelID){
        this.roomID = roomID;
        this.price = price;
        this.capacity = capacity;
        this.seaView = seaView;
        this.hotelID = hotelID;
    }

    /**
     * The getter for the ID of the room record.
     * @return  The ID of the room record
     */
    public int getRoomID(){
        return roomID;
    }

    /**
     * The setter for the ID of the room record.
     * @param roomID    The ID of the room record
     */
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    /**
     * The getter for the cost of the room per day.
     * @return  The cost of the room per day
     */
    public double getPrice(){
        return price;
    }

    /**
     * The setter for the cost of the room per day (in Euros).
     * @param price     The cost of the room per day
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * The getter for the number of people the room is designated to support.
     * @return  The number of people the room is designated to support
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * The setter for the number of people the room is designated to support.
     * @param capacity  The number of people the room is designated to support
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    /**
     * The getter for the sea view indicator.
     * @return  The boolean indicating whether the room has a sea view
     */
    public boolean getSeaView(){
        return seaView;
    }

    /**
     * The setter for the sea view indicator.
     * @param seaView   A boolean indicating whether the room has a sea view
     */
    public void setSeaView(boolean seaView){
        this.seaView = seaView;
    }

    /**
     * The getter for the ID of the hotel record.
     * @return  The ID of the hotel record
     */
    public int getHotelID(){
        return hotelID;
    }

    /**
     * The setter for the ID of the hotel record.
     * @param hotelID   The ID of the hotel record
     */
    public void setHotelID(int hotelID){
        this.hotelID = hotelID;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return A String containing the ID of the room and the hotel in which it is located
     */
    @Override
    public String toString(){
        return "Room " + this.roomID + " in hotel " + this.hotelID;
    }
}