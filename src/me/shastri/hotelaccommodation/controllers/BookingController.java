package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Booking;
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
public class BookingController {
    private static Connection conn = DBManager.getInstance().getConnection();

    /*
    * Create Methods
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
    * Read Methods
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
    * Update Methods
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
    * Delete Methods
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
                JOptionPane.showMessageDialog(null, "\nThe booking with the primary key of " + bookingID + " was successfully deleted.");
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


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /*
        CLI Methods
    */

    /**
     * This method acquires the necessary information to make a new Booking object from the user.
     *
     * @throws SQLException  The called method insertBooking() can throw an SQL exception
     */
    public static void requestNewBookingData() throws SQLException {
        // Create a Booking object using user input
        Booking booking = new Booking(UserInput.reqInt("Enter the guest ID"),
                UserInput.reqDate("Enter the arrival time"),
                UserInput.reqDate("Enter the departure time")
        );
        // Attempt to add the booking to the database and store the outcome
        boolean insertBooking = insertBooking(booking);
        // Inform the user of the outcome
        // Note: Success is handled by the calling method (this method),
        //       while a failure is handled by the called method (insertBooking)
        if (insertBooking) {
            System.out.println("A new row with the primary key of " + booking.getBookingID() + " was successfully inserted.");
        }
    }

    /**
     * This method retrieves a row from the booking table and outputs the values (sans the ID) to the console.
     *
     * @param bookingID The integer referring to the ID of the requested booking
     * @throws SQLException if a database error occurs
     */
    public static void viewBookingRow(int bookingID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKINGROW);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure BOOKINGROW
            stmt.setInt(1, bookingID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the booking record from the booking table, then output it to the console
            if (result.next()) {
                StringBuffer rowData = new StringBuffer();
                rowData.append("guestID: ").append(result.getInt("guestID")).append("\n");
                System.out.print(rowData);
            } else {
                // Follow the "visibility of system status" heuristic
                System.out.println("No booking was found with an ID of " + bookingID);
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            System.out.println(e);
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if (result != null) {
                result.close();
            }
        }
    }

    /**
     * This method retrieves all of the rows of the booking table from the database and outputs them to the console.
     *
     * @throws SQLException if a database error occurs
     */
    public static void viewTableContents() throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKING);) {
            // Execute the query
            result = stmt.executeQuery();
            System.out.println("The contents of the booking table are as follows: " + "\n");
            // Loop through the returned rows
            while (result.next()) {
                // Output each row as a string after using a StringBuffer object to create the string
                StringBuffer rowData = new StringBuffer();
                rowData.append("Booking ID: ").append(result.getString("bookingID")).append("\n");
                rowData.append("Guest ID: ").append(result.getInt("guestID")).append("\n");
                System.out.print(rowData);
                // If there is another row, output white space before returning the cursor to that row
                if (result.next()) {
                    System.out.println();
                    result.previous();
                }
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            System.out.println(e);
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if (result != null) {
                result.close();
            }
        }
    }

    /**
     * This method controls how the process to update a booking in the CLI version of this application is handled.
     *
     * @throws SQLException if a database error occurs
     */
    public static void updateBookingHandler() throws SQLException {
        try {
            // Acquire the booking ID from the user
            int bookingID = UserInput.reqInt("Please enter the ID of the booking you want to modify below.\nBooking ID");
            System.out.println();
            // Acquire the booking from the database requested by the user
            Booking booking = getBookingRow(bookingID);
            // If the requested booking was not found, inform the user
            if (booking == null) {
                System.out.println("No booking was found with an ID of " + bookingID);
                return;
            }
            // If the booking was found, display the results
            // Note: The valus for the booking should be output directly.
            //       Instead, currently they are done using viewBookingRow() which involves another call to the database
            System.out.println("The details of the booking you selected are as follows:");
            viewBookingRow(bookingID);
            // Whitespace
            System.out.println();
            // Call another requestBookingData() to actually update the booking
            requestBookingData(bookingID);
        } catch (NumberFormatException e) {
            // Follow the "visibility of system status" heuristic
            // Inform the user they did not enter a valid integer as the booking ID
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }

    /**
     * This method acquires data from the user to be used to update a specified row in the booking table in the database.
     *
     * @param bookingID The ID of the requested booking
     * @throws SQLException The called method updateBooking() can throw an SQL exception if a database error occurs
     */
    public static void requestBookingData(int bookingID) throws SQLException {
        // Create a Booking object via user input
        Booking booking = new Booking(bookingID,
                UserInput.reqInt("Enter the guest ID"),
                UserInput.reqDate("Enter the guest's arrival date"),
                UserInput.reqDate("Enter the guest's departure date")
        );
        // Call updateBooking() to update the booking in the database
        boolean result = updateBooking(booking);
        // Inform the user of the outcome
        // Note: Success is handled by the calling method (this method),
        //       while a failure is handled by the called method (updateBooking)
        if (result) {
            System.out.println("\nThe booking with the primary key of " + booking.getBookingID() + " was successfully updated.");
        }
    }
}
