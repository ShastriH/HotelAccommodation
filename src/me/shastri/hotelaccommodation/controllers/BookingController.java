package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;

public class BookingController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create
    */
    public static void requestNewBookingData() throws SQLException {
        Booking booking = new Booking();
        booking.setRoomID(UserInput.reqInt("Enter the room ID"));
        booking.setArrival(UserInput.reqDate("Enter the guest's arrival time"));
        booking.setDeparture(UserInput.reqDate("Enter the guest's departure time"));
        boolean insertBooking = insertBooking(booking);
        if (insertBooking) {
            System.out.println("A new row with the primary key of " + booking.getBookingID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertBooking(Booking booking) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTBOOKING, Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setInt(1, booking.getRoomID());
            stmt.setDate(2, booking.getArrival());
            stmt.setDate(3, booking.getDeparture());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                booking.setBookingID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the booking failed.");
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
    public static void viewBookingRow(int bookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKINGROW);){
            stmt.setInt(1, bookingID);
            result = stmt.executeQuery();
            
            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("roomID: ").append(result.getInt("roomID")).append("\n");
                rowData.append("Arrival time: ").append(result.getDate("arrivalTime")).append("\n");
                rowData.append("Departure time: ").append(result.getDate("departureTime")).append("\n");
                System.out.print(rowData);
            } else {
                System.err.println("No booking was found with an ID of " + bookingID);
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
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKING);){
            result = stmt.executeQuery();
            System.out.println("The contents of the booking table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("Booking ID: ").append(result.getString("bookingID")).append("\n");
                rowData.append("Room ID: ").append(result.getInt("roomID")).append("\n");
                rowData.append("Arrival time: ").append(result.getDate("arrivalTime")).append("\n");
                rowData.append("Departure time: ").append(result.getDate("departureTime")).append("\n");
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
    
    public static Booking getBookingRow(int bookingID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.BOOKINGROW);){
            stmt.setInt(1, bookingID);
            result = stmt.executeQuery();
            
            if(result.next()){
                Booking booking = new Booking(
                        bookingID,
                        result.getInt("bookingID"),
                        result.getDate("arrivalTime"),
                        result.getDate("departureTime")
                );
                return booking;
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
    public static void updateBookingHandler() throws SQLException {
        try {
            int bookingID = UserInput.reqInt("Please enter the ID of the booking you want to modify below.\nBooking ID");
            System.out.println();
            Booking booking = getBookingRow(bookingID);
            if (booking == null) {
                System.err.println("No booking was found with an ID of " + bookingID);
                return;
            }
            System.out.println("The details of the booking you selected are as follows:");
            viewBookingRow(bookingID);
            System.out.println();
            requestBookingData(bookingID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }
    
    public static void requestBookingData(int bookingID) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingID(bookingID);
        booking.setRoomID(UserInput.reqInt("Enter the room ID"));
        booking.setArrival(UserInput.reqDate("Enter the guest's arrival time"));
        booking.setDeparture(UserInput.reqDate("Enter the guest's departure time"));
        boolean updateBooking = updateBooking(booking);
        if(updateBooking){
            System.out.println("\nThe booking with the primary key of " + booking.getBookingID() + " was successfully updated.");
        }
    }
    
    public static boolean updateBooking(Booking booking) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEBOOKING);) {
            
            stmt.setInt(1, booking.getRoomID());
            stmt.setDate(2, booking.getArrival());
            stmt.setDate(3, booking.getDeparture());
            stmt.setInt(4, booking.getBookingID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the booking failed.");
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
    public static void deleteBooking(int bookingID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEBOOKING);) {
            
            stmt.setInt(1, bookingID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe booking with the primary key of " + bookingID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
}
