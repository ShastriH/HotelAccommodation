package com.shastri.hotelaccommodation.controllers;

import com.shastri.hotelaccommodation.util.DBManager;
import com.shastri.hotelaccommodation.beans.GuestBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;

public class GuestBookingController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create
    */
    public static void requestNewGuestBookingData() throws SQLException {
        GuestBooking guestBooking = new GuestBooking();
        guestBooking.setBookingID(UserInput.reqInt("Enter the booking ID"));
        guestBooking.setGuestID(UserInput.reqInt("Does the guest ID"));
        boolean insertGuestBooking = insertGuestBooking(guestBooking);
        if (insertGuestBooking) {
            System.out.println("A new row with the primary key of " + guestBooking.getGuestBookingID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertGuestBooking(GuestBooking guestBooking) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTGUESTBOOKING, Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setInt(1, guestBooking.getBookingID());
            stmt.setInt(2, guestBooking.getGuestID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                guestBooking.setGuestBookingID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the guest-booking failed.");
                return false;
            }
        } catch(SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if(key != null) key.close();
        }
        return true;
    }
    
    
    /*
    * Read
    */
    public static void viewGuestBookingRow(int guestBookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTBOOKINGROW);){
            stmt.setInt(1, guestBookingID);
            result = stmt.executeQuery();
            
            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("guestBookingID: ").append(guestBookingID).append("\n");
                rowData.append("Booking ID: ").append(result.getInt("bookingID")).append("\n");
                rowData.append("Guest ID: ").append(result.getInt("guestID")).append("\n");
                System.out.print(rowData);
            } else {
                System.err.println("No guest-booking was found with an ID of " + guestBookingID);
            }
        } catch(SQLException e) {
            System.err.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }
    
    public static void viewTableContents() throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTBOOKING);){
            result = stmt.executeQuery();
            System.out.println("The contents of the guest-booking table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("guestBookingID: ").append(result.getDouble("guestBookingID")).append("\n");
                rowData.append("Booking ID: ").append(result.getInt("bookingID")).append("\n");
                rowData.append("Guest ID: ").append(result.getInt("guestID")).append("\n");
                System.out.print(rowData);
                if(result.next()){
                    System.out.println();
                    result.previous();
                }
            }
        } catch(SQLException e) {
            System.err.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }
    
    public static GuestBooking getGuestBookingRow(int guestBookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTBOOKINGROW);){
            stmt.setInt(1, guestBookingID);
            result = stmt.executeQuery();
            
            if(result.next()){
                GuestBooking guestBooking = new GuestBooking(
                        guestBookingID,
                        result.getInt("bookingID"),
                        result.getInt("guestID")
                );
                return guestBooking;
            } else {
                System.err.println("The requested row was not found.");
                return null;
            }
        } catch(SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if(result != null){
                result.close();
            }
        }
    }
    
    
    /*
    * Update
    */
    public static void updateGuestBookingHandler() throws SQLException {
        try {
            int guestBookingID = UserInput.reqInt("Please enter the ID of the guest-booking you want to modify below.\nGuest-booking ID");
            System.out.println();
            GuestBooking guestBooking = getGuestBookingRow(guestBookingID);
            if (guestBooking == null) {
                System.err.println("No guest-booking was found with an ID of " + guestBookingID);
                return;
            }
            System.out.println("The details of the guest-booking you selected are as follows:");
            viewGuestBookingRow(guestBookingID);
            System.out.println();
            requestGuestBookingData(guestBookingID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }
    
    public static void requestGuestBookingData(int guestBookingID) throws SQLException {
        GuestBooking guestBooking = new GuestBooking();
        guestBooking.setGuestBookingID(guestBookingID);
        guestBooking.setBookingID(UserInput.reqInt("Enter the booking ID"));
        guestBooking.setGuestID(UserInput.reqInt("Does the guest ID"));
        boolean updateGuestBooking = updateGuestBooking(guestBooking);
        if(updateGuestBooking){
            System.out.println("\nThe guest-booking with the primary key of " + guestBooking.getGuestBookingID() + " was successfully updated.");
        }
    }
    
    public static boolean updateGuestBooking(GuestBooking guestBooking) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEGUESTBOOKING);) {
            
            stmt.setInt(1, guestBooking.getBookingID());
            stmt.setInt(2, guestBooking.getGuestID());
            stmt.setInt(3, guestBooking.getGuestBookingID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the guest-booking failed.");
                return false;
            }
        } catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    
    /*
    * Delete
    */
    public static void deleteGuestBooking(int guestBookingID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEGUESTBOOKING);) {
            
            stmt.setInt(1, guestBookingID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe guest-booking with the primary key of " + guestBookingID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
}
