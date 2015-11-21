package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Guest;
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
public class GuestController {
    private static Connection conn = DBManager.getInstance().getConnection();

    /*
    * Create Method
    */

    /**
     * This method executes the insert of a guest into the database using a Guest object's fields as the values for the SQL statement.
     *
     * @param guest     The guest object containing the data to be inserted into the database as a new row in the guest table
     * @return  The indication of whether the method succeeded or failed to add a guest to the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean insertGuest(Guest guest) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTGUEST, Statement.RETURN_GENERATED_KEYS);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure INSERTGUEST
            stmt.setString(1, guest.getTitle());
            stmt.setString(2, guest.getFirstName());
            stmt.setString(3, guest.getLastName());
            stmt.setString(4, guest.getAddress());
            stmt.setString(5, guest.getEmail());
            stmt.setString(6, guest.getPhone());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (guest ID) as the Guest object's guest ID
                guest.setGuestID(key.getInt(1));
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to insert the guest failed.");
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
     * This method retrieves a row from the guest table, creates a Guest object using that data and returns it.
     *
     * @param guestID   The integer referring to the ID of the requested guest
     * @return  The requested guest as a Guest object
     * @throws SQLException     if a database error occurs
     */
    public static Guest getGuestRow(int guestID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTROW);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure GUESTROW
            stmt.setInt(1, guestID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the guest record from the guest table, then use it to create a Guest object
            if(result.next()){
                Guest guest = new Guest(
                        result.getInt("guestID"),
                        result.getString("guestTitle"),
                        result.getString("guestFirstName"),
                        result.getString("guestLastName"),
                        result.getString("guestAddress"),
                        result.getString("guestEmail"),
                        result.getString("guestPhone")
                );
                return guest;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested row was not found.");
                return null;
            }
        } catch(SQLException e) {
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

    /**
     * This method accepts a Guest object and uses its fields as the values to be used to update a row in the guest table in the database.
     *
     * @param guest     The guest object containing the data to be updated
     * @return  The indication of whether the method succeeded or failed to update a guest in the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateGuest(Guest guest) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEGUEST);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEGUEST
            stmt.setString(1, guest.getTitle());
            stmt.setString(2, guest.getFirstName());
            stmt.setString(3, guest.getLastName());
            stmt.setString(4, guest.getAddress());
            stmt.setString(5, guest.getEmail());
            stmt.setString(6, guest.getPhone());
            stmt.setInt(7, guest.getGuestID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the guest failed.");
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
     * This method deletes a row from the guest table in the database corresponding to the specified guest ID.
     *
     * @param guestID   The integer referring to the ID of the guest record to be deleted
     * @return  The indication of whether the method succeeded or failed to remove a guest from the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean deleteGuest(int guestID) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEGUEST);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEGUEST
            stmt.setInt(1, guestID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                JOptionPane.showMessageDialog(null, "\nThe guest with the primary key of " + guestID + " was successfully deleted.");
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested record was not found. Nothing was deleted.");
                return false;
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /*
        CLI Methods
    */

    /**
     * This method acquires the necessary information to make a new Guest object from the user.
     *
     * @throws SQLException
     */
    public static void requestNewGuestData() throws SQLException {
        // Create a Guest object using user input
        Guest guest = new Guest(UserInput.reqString("Enter the guest's title"),
                UserInput.reqString("Enter the guest's first name"),
                UserInput.reqString("Enter the guest's last name"),
                UserInput.reqString("Enter the guest's address"),
                UserInput.reqString("Enter the guest's email"),
                UserInput.reqString("Enter the guest's phone number")
        );
        // Attempt to add the guest to the database and store the outcome
        boolean insertGuest = insertGuest(guest);
        // Inform the user of the outcome
        // Note: Success is handled by the calling method (this method),
        //       while a failure is handled by the called method (insertGuest())
        if (insertGuest) {
            System.out.println("A new row with the primary key of " + guest.getGuestID() + " was successfully inserted.");
        }
    }

    /**
     * This method retrieves a row from the guest table and outputs the values (sans the ID) to the console.
     *
     * @param guestID   The integer referring to the ID of the requested guest
     * @throws SQLException     if a database error occurs
     */
    public static void viewGuestRow(int guestID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTROW);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure GUESTROW
            stmt.setInt(1, guestID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the guest record from the guest table, then output it to the console
            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("Title: ").append(result.getString("guestTitle")).append("\n");
                rowData.append("First name: ").append(result.getString("guestFirstName")).append("\n");
                rowData.append("Last name: ").append(result.getString("guestLastName")).append("\n");
                rowData.append("Address: ").append(result.getString("guestAddress")).append("\n");
                rowData.append("Email: ").append(result.getString("guestEmail")).append("\n");
                rowData.append("Telephone number: ").append(result.getString("guestPhone")).append("\n");
                System.out.print(rowData);
            } else {
                // Follow the "visibility of system status" heuristic
                System.out.println("No guest was found with an ID of " + guestID);
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            System.out.println(e);
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if(result != null){
                result.close();
            }
        }
    }

    /**
     * This method retrieves all of the rows of the guest table from the database and outputs them to the console.
     *
     * @throws SQLException     if a database error occurs
     */
    public static void viewTableContents() throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUEST);){
            // Execute the query
            result = stmt.executeQuery();
            System.out.println("The contents of the guest table are as follows: " + "\n");
            // Loop through the returned rows
            while(result.next()){
                // Output each row as a string after using a StringBuffer object to create the string
                StringBuffer rowData = new StringBuffer();
                rowData.append("Guest ID: ").append(result.getString("guestID")).append("\n");
                rowData.append("Title: ").append(result.getString("guestTitle")).append("\n");
                rowData.append("First name: ").append(result.getString("guestFirstName")).append("\n");
                rowData.append("Last name: ").append(result.getString("guestLastName")).append("\n");
                rowData.append("Address: ").append(result.getString("guestAddress")).append("\n");
                rowData.append("Email: ").append(result.getString("guestEmail")).append("\n");
                rowData.append("Telephone number: ").append(result.getString("guestPhone")).append("\n");
                System.out.print(rowData);
                // If there is another row, output white space before returning the cursor to that row
                if(result.next()){
                    System.out.println();
                    result.previous();
                }
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            System.out.println(e);
        } finally {
            // Ensure the ResultSet object declared at the top of this method is closed
            if(result != null){
                result.close();
            }
        }
    }

    /**
     * This method controls how the process to update a guest in the CLI version of this application is handled.
     *
     * @throws SQLException     if a database error occurs
     */
    public static void updateGuestHandler() throws SQLException {
        try {
            // Acquire the guest ID from the user
            int guestID = UserInput.reqInt("Please enter the ID of the guest you want to modify below.\nGuest ID");
            System.out.println();
            // Acquire the guest from the database requested by the user
            Guest guest = getGuestRow(guestID);
            // If the requested guest was not found, inform the user
            if (guest == null) {
                System.out.println("No guest was found with an ID of " + guestID);
                return;
            }
            // If the guest was found, display the results
            // Note: The valus for the guest should be output directly.
            //       Instead, currently they are done using viewGuestRow() which involves another call to the database
            System.out.println("The details of the guest you selected are as follows:");
            viewGuestRow(guestID);
            // Whitespace
            System.out.println();
            // Call another requestBookingData() to actually update the guest
            requestGuestData(guestID);
        } catch (NumberFormatException e) {
            // Follow the "visibility of system status" heuristic
            // Inform the user they did not enter a valid integer as the guest ID
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }

    /**
     * This method acquires data from the user to be used to update a specified row in the guest table in the database.
     *
     * @param guestID   The ID of the requested guest
     * @throws SQLException     The called method updateGuest() can throw an SQL exception if a database error occurs
     */
    public static void requestGuestData(int guestID) throws SQLException {
        // Create a Guest object via user input
        Guest guest = new Guest(guestID,
                UserInput.reqString("Enter the guest's title"),
                UserInput.reqString("Enter the guest's first name"),
                UserInput.reqString("Enter the guest's last name"),
                UserInput.reqString("Enter the guest's address"),
                UserInput.reqString("Enter the guest's email"),
                UserInput.reqString("Enter the guest's phone number")
        );
        // Call updateGuest() to update the guest in the database
        boolean result = updateGuest(guest);
        // Inform the user of the outcome
        // Note: Success is handled by the calling method (this method),
        //       while a failure is handled by the called method (updateGuest)
        if(result){
            System.out.println("\nThe guest with the primary key of " + guest.getGuestID() + " was successfully updated.");
        }
    }
}
