package me.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri Harrinanan
 */
public class Hotel {
    private int hotelID;
    private String address;


    /**
     * No arguments constructor
     */
    public Hotel(){}

    /**
     * This is the constructor used to create a Hotel object using data provided by
     * the user which is to be inserted as a new row into the hotel table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param address   The address of the hotel
     */
    public Hotel(String address){
        this.address = address;
    }

    /**
     * This is the costructor used to create a complete Hotel obect.
     * @param hotelID   The ID of the hotel record
     * @param address   The address of the hotel
     */
    public Hotel(int hotelID, String address){
        this.hotelID = hotelID;
        this.address = address;
    }

    /**
     * The getter method for the ID of the hotel record.
     * @return  The ID of the hotel record
     */
    public int getHotelID(){
        return hotelID;
    }

    /**
     * The setter for the ID of the hotel record.
     * @param hotelID   The ID of the hotel
     */
    public void setHotelID(int hotelID){
        this.hotelID = hotelID;
    }

    /**
     * The getter for the address of the hotel.
     * @return  The address of the hotel
     */
    public String getAddress(){
        return address;
    }

    /**
     * The setter for the address of the hotel.
     * @param address   The address of the hotel
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return  The ID of the hotel as a String
     */
    @Override
    public String toString(){
        return String.valueOf(this.hotelID);
    }
}
