package me.shastri.hotelaccommodation.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Shastri Harrinanan
 */
public class FormFiller {
    // Get the connection to the database
    private static final Connection conn = DBManager.getInstance().getConnection();
    
    /**
     * Method to build a Model to be used for the booking ID combo boxes.
     * 
     * @return  The Model containing all the IDs of all the booking records in the database
     */
    public static DefaultComboBoxModel getBookingRecordsComboBox() {
        // Create a Model
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        // Prepare the statement and execute the query
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKINGID);
            ResultSet result = stmt.executeQuery();) {
            // Loop through the returned rows
            while(result.next()){
                // Add each booking ID to the Model
                dcbm.addElement(String.valueOf(result.getInt("bookingID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dcbm;
    }
    
    /**
     * Method to build a Model to be used for the guest ID combo boxes.
     * 
     * @return  The Model containing all the IDs of all the guest records in the database
     */
    public static DefaultComboBoxModel getGuestRecordsComboBox() {
        // Create a Model
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        // Prepare the statement and execute the query
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTID);
            ResultSet result = stmt.executeQuery();){
            // Loop through the returned rows
            while(result.next()){
                // Add each guest ID to the Model
                dcbm.addElement(String.valueOf(result.getInt("guestID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dcbm;
    }
    
    /**
     * Method to build a Model to be used for the hotel ID combo boxes.
     * 
     * @return  The Model containing all the IDs of all the hotel records in the database
     */
    public static DefaultComboBoxModel getHotelRecordsComboBox() {
        // Create a Model
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.HOTELID);
            ResultSet result = stmt.executeQuery();) {
            // Loop through the returned rows
            while(result.next()){
                // Add each hotel ID to the Model
                dcbm.addElement(String.valueOf(result.getInt("hotelID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dcbm;
    }
    
    /**
     * Method to build a Model to be used for the manager ID combo boxes.
     * 
     * @return  The Model containing all the IDs of all the manager records in the database
     */
    public static DefaultComboBoxModel getManagerRecordsComboBox() {
        // Create a Model
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        // Prepare the statement and execute the query
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.MANAGERID);
            ResultSet result = stmt.executeQuery();) {
            // Loop through the returned rows
            while(result.next()){
                // Add each manager ID to the Model
                dcbm.addElement(String.valueOf(result.getInt("managerID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dcbm;
    }
    
    /**
     * Method to build a Model to be used for the room ID combo boxes.
     * 
     * @return  The Model containing all the IDs of all the room records in the database
     */
    public static DefaultComboBoxModel getRoomRecordsComboBox() {
        // Create a Model
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        // Prepare the statement and execute the query
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMID);
            ResultSet result = stmt.executeQuery();) {
            // Loop through the returned rows
            while(result.next()){
                // Add each room ID to the Model
                dcbm.addElement(String.valueOf(result.getInt("roomID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dcbm;
    }
    
    /**
     * Method to build a Model to be used for the room ID lists.
     * 
     * @return  The DefaultListModel containing all the IDs of all the room records in the database
     */
    public static DefaultListModel getRoomRecordsList() {
        // Create a DefaultListModel
        DefaultListModel dlm = new DefaultListModel();
        // Prepare the statement and execute the query
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMID);
            ResultSet result = stmt.executeQuery();) {
            // Loop through the returned rows
            while(result.next()){
                // Add each booking ID to the DefaultListModel
                dlm.addElement(String.valueOf(result.getInt("roomID")));
            }
        } catch(Exception e){
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
        return dlm;
    }
}
