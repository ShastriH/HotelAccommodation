package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.beans.Hotel;
import me.shastri.hotelaccommodation.util.DBManager;
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
public class HotelController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create Method
    */

    /**
     * This method executes the insert of a hotel into the database using a Hotel object's
     * fields as the values for the SQL statement.
     *
     * @param hotel     The hotel object containing the data to be inserted into the database as a new row in the hotel table
     * @return          A boolean value to indicate whether the insert was successful
     * @throws SQLException     This method can throw an SQL exception
     */
    public static boolean insertHotel(Hotel hotel) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTHOTEL, Statement.RETURN_GENERATED_KEYS);){
            // Complete the preparation of the SQL statement by assigning the value for the stored procedure INSERTHOTEL
            stmt.setString(1, hotel.getAddress());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (hotel ID) as the Guest object's hotel ID
                hotel.setHotelID(key.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "The attempt to insert the hotel failed.");
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
     * This method retrieves a row from the hotel table, creates a Hotel object using that data and returns it.
     *
     * @param hotelID   The integer referring to the ID of the requested hotel
     * @return  The requested hotel as a Hotel object
     * @throws SQLException     if a database error occurs
     */
    public static Hotel getHotelRow(int hotelID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.HOTELROW);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure HOTELROW
            stmt.setInt(1, hotelID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the hotel record from the hotel table, then use it to create a Hotel object
            if(result.next()){
                Hotel hotel = new Hotel(
                        result.getInt("hotelID"),
                        result.getString("hotelAddress")
                );
                return hotel;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The requested row was not found.");
                return null;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            if(result != null){
                result.close();
            }
        }
    }
    
    
    /*
    * Update Method
    */

    /**
     * This method accepts a Hotel object and uses its fields as the values to be used to update a row in the hotel table
     * in the database.
     *
     * @param hotel     The hotel object containing the data to be updated
     * @return  The indication of whether the method succeeded or failed to update a hotel to the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateHotel(Hotel hotel) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEHOTEL);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEHOTEL
            stmt.setString(1, hotel.getAddress());
            stmt.setInt(2, hotel.getHotelID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the hotel failed.");
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
     * This method deletes a row from the hotel table in the database corresponding to the specified hotel ID.
     *
     * @param hotelID   The integer referring to the ID of the hotel record to be deleted
     * @return  The indication of whether the method succeeded or failed to remove a hotel from the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean deleteHotel(int hotelID) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEHOTEL);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEHOTEL
            stmt.setInt(1, hotelID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "\nThe hotel with the primary key of " + hotelID + " was successfully deleted.");
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
     * This method acquires the necessary information to make a new hotel from the user.
     *
     * @throws SQLException     The called method insertHotel() can throw an SQL exception
     */
    public static void requestNewHotelData() throws SQLException {
        // Create a blank Booking object in order to set the fields individually via user input
        Hotel hotel = new Hotel();
        // Set the field of the object using the custom UserInput class
        hotel.setAddress(UserInput.reqString("Enter the hotel's address"));
        // Attempt to add the hotel to the database and store the outcome
        boolean insertHotel = insertHotel(hotel);
        // Inform the user of the outcome
        // Note: Success is handled by the calling method (this method),
        //       while a failure is handled by the called method (insertHotel)
        if(insertHotel){
            System.out.println("A new row with the primary key of " + hotel.getHotelID() + " was successfully inserted.");
        }
    }

    public static void viewHotelRow(int hotelID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.HOTELROW);){
            stmt.setInt(1, hotelID);
            result = stmt.executeQuery();

            if(result.next()){
                System.out.print("Address: " + result.getString("hotelAddress") + "\n");
            } else {
                System.out.println("No hotel was found with an ID of " + hotelID);
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }

    public static void viewTableContents() throws SQLException{
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.HOTEL);){
            result = stmt.executeQuery();
            System.out.println("The contents of the hotel table are as follows: ");
            while(result.next()){
                StringBuffer tableData = new StringBuffer();
                tableData.append("Hotel ID: ").append(result.getInt("hotelID")).append("\n");
                tableData.append("Address: ").append(result.getString("hotelAddress")).append("\n");
                System.out.println(tableData);
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }

    }

    public static void updateHotelHandler() throws SQLException {
        try {
            int hotelID = UserInput.reqInt("Please enter the ID of the hotel you want to modify below.\nHotel ID");
            System.out.println();
            Hotel hotel = getHotelRow(hotelID);
            if (hotel == null) {
                System.out.println("No hotel was found with an ID of " + hotelID);
                return;
            }
            System.out.println("The details of the hotel you selected are as follows:");
            viewHotelRow(hotelID);
            System.out.println();
            requestHotelData(hotelID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }

    public static void requestHotelData(int hotelID) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotelID(hotelID);
        hotel.setAddress(UserInput.reqString("Enter the hotel's address"));
        boolean updateHotel = updateHotel(hotel);
        if(updateHotel){
            System.out.println("\nThe hotel with the primary key of " + hotel.getHotelID() + " was successfully updated.");
        }
    }
}
