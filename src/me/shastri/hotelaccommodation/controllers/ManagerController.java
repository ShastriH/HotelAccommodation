package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.beans.Manager;
import me.shastri.hotelaccommodation.util.DBManager;
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
public class ManagerController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create Method
    */

    /**
     * This method executes the insert of a manager into the database using a Guest object's fields as the values for the SQL statement.
     *
     * @param manager   The manager object containing the data to be inserted into the database as a new row in the manager table
     * @return  The indication of whether the method succeeded or failed to add a manager to the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean insertManager(Manager manager) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTMANAGER, Statement.RETURN_GENERATED_KEYS);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure INSERTMANAGER
            stmt.setString(1, manager.getFirstName());
            stmt.setString(2, manager.getLastName());
            stmt.setString(3, manager.getEmail());
            stmt.setString(4, manager.getPhone());
            stmt.setInt(5, manager.getHotelID());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (guest ID) as the Guest object's guest ID
                manager.setManagerID(key.getInt(1));
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to insert the manager failed.");
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
     * This method retrieves a row from the manager table, creates a Manager object using that data and returns it.
     * @param managerID     The integer referring to the ID of the requested manager
     * @return  The requested guest as a Manager object
     * @throws SQLException     if a database error occurs
     */
    public static Manager getManagerRow(int managerID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.MANAGERROW);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure MANAGERROW
            stmt.setInt(1, managerID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the manager record from the manager table, then use it to create a Manager object
            if(result.next()){
                Manager manager = new Manager(
                        result.getInt("managerID"),
                        result.getString("managerFirstName"),
                        result.getString("managerLastName"),
                        result.getString("managerEmail"),
                        result.getString("managerPhone"),
                        result.getInt("hotelID")
                );
                return manager;
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
     * This method accepts a Manager object and uses its fields as the values to be used to update a row in the manager table in the database.
     *
     * @param manager   The manager object containing the data to be updated
     * @return  The indication of whether the method succeeded or failed to update a manager in the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateManager(Manager manager) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEMANAGER);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEMANAGER
            stmt.setString(1, manager.getFirstName());
            stmt.setString(2, manager.getLastName());
            stmt.setString(3, manager.getEmail());
            stmt.setString(4, manager.getPhone());
            stmt.setInt(5, manager.getHotelID());
            stmt.setInt(6, manager.getManagerID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the manager failed.");
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
     * This method deletes a row from the manager table in the database corresponding to the specified manager ID.
     *
     * @param managerID     The integer referring to the ID of the manager record to be deleted
     * @return  The indication of whether the method succeeded or failed to remove a manager from the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean deleteManager(int managerID) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEMANAGER);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEMANAGER
            stmt.setInt(1, managerID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                System.out.println("\nThe manager with the primary key of " + managerID + " was successfully deleted.");
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
