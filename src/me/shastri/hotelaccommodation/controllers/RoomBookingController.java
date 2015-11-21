package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.RoomBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import me.shastri.libs.UserInput;

/**
 *
 * @author Shastri
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


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /*
        CLI Methods
    */

    public static void requestNewRoomBookingData() throws SQLException {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setRoomID(UserInput.reqInt("Does the room ID"));
        roomBooking.setBookingID(UserInput.reqInt("Enter the booking ID"));
        boolean insertRoomBooking = insertRoomBooking(roomBooking);
        if (insertRoomBooking) {
            System.out.println("A new row with the primary key of " + roomBooking.getRoomBookingID() + " was successfully inserted.");
        }
    }

    public static void viewRoomBookingRow(int roomBookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMBOOKINGROW);){
            stmt.setInt(1, roomBookingID);
            result = stmt.executeQuery();

            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("roomBookingID: ").append(roomBookingID).append("\n");
                rowData.append("Room ID: ").append(result.getInt("roomID")).append("\n");
                rowData.append("Booking ID: ").append(result.getInt("bookingID")).append("\n");
                rowData.append("Arrival Date: ").append(result.getDate("arrivalDate")).append("\n");
                rowData.append("Departure Date: ").append(result.getDate("departureDate")).append("\n");
                System.out.print(rowData);
            } else {
                System.out.println("No room-booking was found with an ID of " + roomBookingID);
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }

    public static void viewTableContents() throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMBOOKING);){
            result = stmt.executeQuery();
            System.out.println("The contents of the room-booking table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("roomBookingID: ").append(result.getDouble("roomBookingID")).append("\n");
                rowData.append("Room ID: ").append(result.getInt("roomID")).append("\n");
                rowData.append("Booking ID: ").append(result.getInt("bookingID")).append("\n");
                rowData.append("Arrival Date: ").append(result.getDate("arrivalDate")).append("\n");
                rowData.append("Departure Date: ").append(result.getDate("departureDate")).append("\n");
                System.out.print(rowData);
                if(result.next()){
                    System.out.println();
                    result.previous();
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }

    public static RoomBooking getRoomBookingRow(int roomBookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMBOOKINGROW);){
            stmt.setInt(1, roomBookingID);
            result = stmt.executeQuery();

            if(result.next()){
                RoomBooking roomBooking = new RoomBooking(
                        roomBookingID,
                        result.getInt("roomID"),
                        result.getInt("bookingID"));
                return roomBooking;
            } else {
                System.out.println("The requested row was not found.");
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if(result != null){
                result.close();
            }
        }
    }

    public static void updateRoomBookingHandler() throws SQLException {
        try {
            int roomBookingID = UserInput.reqInt("Please enter the ID of the room-booking you want to modify below.\nGuest-booking ID");
            System.out.println();
            RoomBooking roomBooking = getRoomBookingRow(roomBookingID);
            if (roomBooking == null) {
                System.out.println("No room-booking was found with an ID of " + roomBookingID);
                return;
            }
            System.out.println("The details of the room-booking you selected are as follows:");
            viewRoomBookingRow(roomBookingID);
            System.out.println();
            requestRoomBookingData(roomBookingID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }

    public static void requestRoomBookingData(int roomBookingID) throws SQLException {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setRoomBookingID(roomBookingID);
        roomBooking.setBookingID(UserInput.reqInt("Enter the booking ID"));
        roomBooking.setRoomID(UserInput.reqInt("Does the room ID"));
        boolean updateRoomBooking = updateRoomBooking(roomBooking);
        if(updateRoomBooking){
            System.out.println("\nThe room-booking with the primary key of " + roomBooking.getRoomBookingID() + " was successfully updated.");
        }
    }

    public static void deleteRoomBooking(int roomBookingID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEROOMBOOKING);) {

            stmt.setInt(1, roomBookingID);

            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe room-booking with the primary key of " + roomBookingID + " was successfully deleted.");
            } else {
                System.out.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
