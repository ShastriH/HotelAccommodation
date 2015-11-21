package me.shastri.hotelaccommodation.beans;

import java.sql.Date;

/**
 *
 * @author Shastri
 */
public class Booking {
    private int bookingID = 0;
    private int guestID = 0;
    private Date arrivalDate = null;
    private Date departureDate = null;

    /**
     * No arguments constructor.
     */
    public Booking(){}

    /**
     * This is the constructor used to create a Booking object using data provided by
     * the user which is to be inserted as a new row into the booking table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param guestID           The ID of the guest which requested the booking
     * @param arrivalDate       The date the guest is due to arrive at the hotel
     * @param departureDate     The date the guest is due to leave the hotel
     */
    public Booking(int guestID, Date arrivalDate, Date departureDate){
        this.guestID = guestID;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    /**
     * This is the constructor used to create a complete Booking object.
     * @param bookingID         The ID of the guest record in the booking table in the database
     * @param guestID           The ID of the guest which requested the booking
     * @param arrivalDate       The date the guest is due to arrive at the hotel
     * @param departureDate     The date the guest is due to leave the hotel
     */
    public Booking(int bookingID, int guestID, Date arrivalDate, Date departureDate){
        this.bookingID = bookingID;
        this.guestID = guestID;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    /**
     * The getter for the ID of the booking record.
     * @return  The ID of the booking record
     */
    public int getBookingID(){
        return bookingID;
    }

    /**
     * The setter for the ID of the booking record.
     * @param bookingID     The ID of the booking
     */
    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    /**
     * The getter for the ID of the guest who requested the booking.
     * @return  The ID of the guest who requested the booking
     */
    public int getGuestID(){
        return guestID;
    }

    /**
     * The setter for the guest's ID.
     * @param guestID   The ID of the guest who requested the booking
     */
    public void setGuestID(int guestID){
        this.guestID = guestID;
    }

    /**
     * The getter for the arrival date of the guest who requested the booking.
     * @return  The date the guest who requested the booking is due to arrive at the hotel
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * The setter for the arrival date of the guest who requested the booking.
     * @param arrivalDate   The date the guest who requested the booking is due to arrive at the hotel
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * The getter for the departure date of the guest who requested the booking.
     * @return  The date the guest who requested the booking is due to leave the hotel
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * The setter for the departure date of the guest who requested the booking.
     * @param departureDate     The date the guest who requested the booking is due to leave the hotel
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return The ID of the booking record as a String
     */
    @Override
    public String toString(){
        return String.valueOf(this.bookingID);
    }
}
