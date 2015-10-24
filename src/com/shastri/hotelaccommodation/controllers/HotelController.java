package com.shastri.hotelaccommodation.controllers;

// @author Shastri

import com.shastri.hotelaccommodation.beans.Hotel;
import com.shastri.hotelaccommodation.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;


public class HotelController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create
    */
    public static void requestNewHotelData() throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setAddress(UserInput.reqString("Enter the hotel's address"));
        boolean insertHotel = insertHotel(hotel);
        if(insertHotel){
            System.out.println("A new row with the primary key of " + hotel.getHotelID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertHotel(Hotel hotel) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTHOTEL, Statement.RETURN_GENERATED_KEYS);){

            stmt.setString(1, hotel.getAddress());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                hotel.setHotelID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the hotel failed.");
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
    public static void viewHotelRow(int hotelID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.HOTELROW);){
            stmt.setInt(1, hotelID);
            result = stmt.executeQuery();
            
            if(result.next()){
                System.out.print("Address: " + result.getString("hotelAddress") + "\n");
            } else {
                System.err.println("No hotel was found with an ID of " + hotelID);
            }
        } catch(SQLException e) {
            System.err.println(e);
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
            System.err.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
        
    }
    
    public static Hotel getHotelRow(int hotelID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.HOTELROW);){
            stmt.setInt(1, hotelID);
            result = stmt.executeQuery();
            
            if(result.next()){
                Hotel hotel = new Hotel(
                        result.getInt("hotelID"),
                        result.getString("hotelAddress")
                );
                return hotel;
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
    public static void updateHotelHandler() throws SQLException {
        try {
            int hotelID = UserInput.reqInt("Please enter the ID of the hotel you want to modify below.\nHotel ID");
            System.out.println();
            Hotel hotel = getHotelRow(hotelID);
            if (hotel == null) {
                System.err.println("No hotel was found with an ID of " + hotelID);
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
    
    public static boolean updateHotel(Hotel hotel) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEHOTEL);) {
            
            stmt.setString(1, hotel.getAddress());
            stmt.setInt(2, hotel.getHotelID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the hotel failed.");
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
    public static void deleteHotel(int hotelID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEHOTEL);) {
            
            stmt.setInt(1, hotelID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe hotel with the primary key of " + hotelID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
}
