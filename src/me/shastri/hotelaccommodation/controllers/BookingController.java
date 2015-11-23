package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Booking;
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
public class BookingController {
    private static Connection conn = DBManager.getInstance().getConnection();

    /*
    * Create Method
    */

    /**
     * This method executes the insert of a booking into the database using a Booking object's fields as the values for the SQL statement.
     *
     * @param booking The booking object containing the data to be inserted into the database as a new row in the booking table
     * @return The indication of whether the method succeeded or failed to add a booking to the database
     * @throws SQLException if a database error occurs
     */
    public static boolean insertBooking(Booking booking) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTBOOKING, Statement.RETURN_GENERATED_KEYS);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure INSERTBOOKING
            stmt.setInt(1, booking.getGuestID());
            stmt.setDate(2, booking.getArrivalDate());
            stmt.setDate(3, booking.getDepartureDate());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if (numAffectedRows == 1) {
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (booking ID) as the Booking object's booking ID
                booking.setBookingID(key.getInt(1));
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to insert the booking failed.");
                return false;
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if (key != null) {
                key.close();
            }
        }
        return true;
    }
    
    
    /*
    * Read Method
    */

    /**
     * This method retrieves a row from the booking table, creates a Booking object using that data and returns it.
     *
     * @param bookingID The integer referring to the ID of the requested booking
     * @return The requested booking as a Booking object
     * @throws SQLException if a database error occurs
     */
    public static Booking getBookingRow(int bookingID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKINGROW);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure BOOKINGROW
            stmt.setInt(1, bookingID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the booking record from the booking table, then use it to create a Booking object
            if (result.next()) {
                Booking booking = new Booking(bookingID,
                        result.getInt("guestID"),
                        result.getDate("arrivalDate"),
                        result.getDate("departureDate"));
                return booking;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested row was not found.");
                return null;
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if (result != null) {
                result.close();
            }
        }
    }
    
    
    /*
    * Update Method
    */

    /**
     * This method accepts a Booking object and uses its fields as the values to be used to update a row in the booking table in the database.
     *
     * @param booking The booking object containing the data to be updated
     * @return The indication of whether the method succeeded or failed to update a booking in the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateBooking(Booking booking) throws SQLException {
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEBOOKING);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEBOOKING
            stmt.setInt(1, booking.getGuestID());
            stmt.setDate(2, booking.getArrivalDate());
            stmt.setDate(3, booking.getDepartureDate());
            stmt.setInt(4, booking.getBookingID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if (numAffectedRows == 1) {
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the booking failed.");
                return false;
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    
    /*
    * Delete Method
    */

    /**
     * This method deletes a row from the booking table in the database corresponding to the specified booking ID.
     *
     * @param bookingID The integer referring to the ID of the booking record to be deleted
     * @return The indication of whether the method succeeded or failed to remove a booking from the database
     * @throws SQLException if a database error occurs
     */
    public static boolean deleteBooking(int bookingID) throws SQLException {
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEBOOKING);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEBOOKING
            stmt.setInt(1, bookingID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if (numAffectedRows == 1) {
                System.out.println("\nThe booking with the primary key of " + bookingID + " was successfully deleted.");
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested record was not found. Nothing was deleted.");
                return false;
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return true;
    }
}
