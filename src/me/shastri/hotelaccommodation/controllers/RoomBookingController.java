package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.RoomBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Shastri Harrinanan
 */
public class RoomBookingController {
    private static Connection conn = DBManager.getInstance().getConnection();

    /*
    * Create Method
    */

    /**
     * This method executes the insert of a room-booking into the database using a RoomBooking object's fields as the values for the SQL statement.
     *
     * @param roomBooking   The room-booking object containing the data to be inserted into the database as a new row in the room-booking table
     * @return  The indication of whether the method succeeded or failed to add a room-booking to the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean insertRoomBooking(RoomBooking roomBooking) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTROOMBOOKING, Statement.RETURN_GENERATED_KEYS);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure INSERTROOMBOOKING
            stmt.setInt(1, roomBooking.getRoomID());
            stmt.setInt(2, roomBooking.getBookingID());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (room-booking ID) as the RoomBooking object's room-booking ID
                roomBooking.setRoomBookingID(key.getInt(1));
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to insert the room-booking failed.");
                return false;
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if(key != null) {
                key.close();
            }
        }
        return true;
    }
    
    
    /*
    * Read Method
    */

    /**
     * This method retrieves a row from the room_booking table, creates a RoomBooking object using that data and returns it.
     *
     * @param bookingID     The integer referring to the ID of the requested booking for the requested rooms
     * @return  The list of rooms for the requested booking as a String
     * @throws SQLException     if a database error occurs
     */
    public static String getRooms(int bookingID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMBOOKINGROOMS);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure ROOMBOOKINGROOMS
            stmt.setInt(1, bookingID);
            // Execute the query
            result = stmt.executeQuery();
            // Create a StringBuilder object to use to build the list of rooms
            StringBuilder rooms = new StringBuilder();
            // If the query successfully retrieved the rooom-booking records from the room_booking table, go through the rows to create a list of the rooms
            while(result.next()){
                rooms.append(result.getInt("roomID")).append(",");
            }
            // Create a String object so the substring() method can be used
            String roomList = rooms.toString();
            // Remove the trailing "," from the list of rooms
            roomList = roomList.substring(0, roomList.length() - 1);
            // Return the formatted list of rooms
            return roomList;
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if(result != null){
                result.close();
            }
        }
    }
    
    
    /*
    * Update Method
    */

    // Note: This method is not used in the application. It is only here to ensure all CRUD operations are present for all controllers.

    /**
     * This method accepts a RoomBooking object and uses its fields as the values to be used to update the appropriate row(s) in the room_booking table in the database.
     * @param roomBooking   The RoomBooking object containing the data to be updated
     * @return  The indication of whether the method succeeded or failed to update a room-booking in the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateRoomBooking(RoomBooking roomBooking) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEROOMBOOKING);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEROOMBOOKING
            stmt.setInt(1, roomBooking.getRoomID());
            stmt.setInt(2, roomBooking.getBookingID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the room-booking failed.");
                return false;
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    
    /*
    * Delete Method
    */

    /**
     * This method deletes room-bookings from the room_booking table in the database corresponding to the specified room-booking ID.
     *
     * @param bookingID     The integer referring to the ID of the booking for the room-bookings to be deleted
     * @return  The indication of whether the method succeeded or failed to remove the room-bookings from the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean deleteRoomBookings(int bookingID) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEROOMBOOKINGS);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEROOMBOOKINGS
            stmt.setInt(1, bookingID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful and only one room was attached to the designated booking, one row would have been affected
            if(numAffectedRows == 1){
                System.out.println("\nThe room-booking for the booking with the primary key of " + bookingID + " was successfully deleted.");
            } else if(numAffectedRows > 1) {
                    // If the delete was successful and multiple rooms were attached to the designated booking, multiple rows would have been affected
                    System.out.println("\nThe room-bookings for the booking with the primary key of " + bookingID + " were successfully deleted.");
                } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested records were not found. Nothing was deleted.");
                return false;
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return true;
    }
}
