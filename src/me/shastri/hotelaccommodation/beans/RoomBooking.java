package me.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri
 */
public class RoomBooking {
    private int roomBookingID = 0;
    private int roomID = 0;
    private int bookingID = 0;

    /**
     * No arguments constructor.
     */
    public RoomBooking(){}

    /**
     * This is the constructor used to create a Room-Booking object using data provided by
     * the user which is to be inserted as a new row into the room_booking table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param roomID        The ID of a room the guest requested when they requested the booking
     * @param bookingID     The ID of the booking for which the Room-Booking object was made
     */
    public RoomBooking(int roomID, int bookingID){
        this.roomID = roomID;
        this.bookingID = bookingID;
    }

    /**
     * This is the constructor used to create a complete RoomBooking object.
     * @param roomBookingID     The ID of the room_booking record
     * @param roomID            The ID of a room the guest requested when they requested the booking
     * @param bookingID         The ID of the booking for which the Room-Booking object was made
     */
    public RoomBooking(int roomBookingID, int roomID, int bookingID){
        this.roomBookingID = roomBookingID;
        this.roomID = roomID;
        this.bookingID = bookingID;
    }

    /**
     * The getter for the ID of the room_booking record.
     * @return  The ID of the room_booking record
     */
    public int getRoomBookingID(){
        return roomBookingID;
    }

    /**
     * The setter for the ID of the room_booking record.
     * @param roomBookingID     The ID of the room_booking record
     */
    public void setRoomBookingID(int roomBookingID){
        this.roomBookingID = roomBookingID;
    }

    /**
     * The getter for the ID of a room the guest requested when they requested the booking.
     * @return  The ID of a room the guest requested when they requested the booking
     */
    public int getRoomID(){
        return roomID;
    }

    /**
     *  The setter for the ID of a room the guest requested when they requested the booking.
     * @param roomID    The ID of a room the guest requested when they requested the booking
     */
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    /**
     * The getter for the ID of the booking record for which the RoomBooking object was made.
     * @return  The ID of the booking record for which the RoomBooking object was made
     */
    public int getBookingID(){
        return bookingID;
    }

    /**
     * The setter for the ID of the booking record for which the RoomBooking object was made.
     * @param bookingID     The ID of the booking record for which the RoomBooking object was made
     */
    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return The ID of the room_booking record as a String
     */
    @Override
    public String toString(){
        return String.valueOf(this.roomBookingID);
    }
}
