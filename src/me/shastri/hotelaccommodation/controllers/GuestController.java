package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Guest;
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
                System.out.println("\nThe guest with the primary key of " + guestID + " was successfully deleted.");
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
}
