package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;

public class GuestController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create
    */
    public static void requestNewGuestData() throws SQLException {
        Guest guest = new Guest();
        guest.setTitle(UserInput.reqString("Enter the guest's title"));
        guest.setFirstName(UserInput.reqString("Enter the guest's first name"));
        guest.setLastName(UserInput.reqString("Enter the guest's last name"));
        guest.setAddress(UserInput.reqString("Enter the guest's address"));
        guest.setEmail(UserInput.reqString("Enter the guest's email"));
        guest.setPhone(UserInput.reqString("Enter the guest's phone number"));
        boolean insertGuest = insertGuest(guest);
        if (insertGuest) {
            System.out.println("A new row with the primary key of " + guest.getGuestID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertGuest(Guest guest) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTGUEST, Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setString(1, guest.getTitle());
            stmt.setString(2, guest.getFirstName());
            stmt.setString(3, guest.getLastName());
            stmt.setString(4, guest.getAddress());
            stmt.setString(5, guest.getEmail());
            stmt.setString(6, guest.getPhone());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                guest.setGuestID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the guest failed.");
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
    public static void viewGuestRow(int guestID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTROW);){
            stmt.setInt(1, guestID);
            result = stmt.executeQuery();
            
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
                System.err.println("No guest was found with an ID of " + guestID);
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
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUEST);){
            result = stmt.executeQuery();
            System.out.println("The contents of the guest table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("Guest ID: ").append(result.getString("guestID")).append("\n");
                rowData.append("Title: ").append(result.getString("guestTitle")).append("\n");
                rowData.append("First name: ").append(result.getString("guestFirstName")).append("\n");
                rowData.append("Last name: ").append(result.getString("guestLastName")).append("\n");
                rowData.append("Address: ").append(result.getString("guestAddress")).append("\n");
                rowData.append("Email: ").append(result.getString("guestEmail")).append("\n");
                rowData.append("Telephone number: ").append(result.getString("guestPhone")).append("\n");
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
    
    public static Guest getGuestRow(int guestID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.GUESTROW);){
            stmt.setInt(1, guestID);
            result = stmt.executeQuery();
            
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
    public static void updateGuestHandler() throws SQLException {
        try {
            int guestID = UserInput.reqInt("Please enter the ID of the guest you want to modify below.\nGuest ID");
            System.out.println();
            Guest guest = getGuestRow(guestID);
            if (guest == null) {
                System.err.println("No guest was found with an ID of " + guestID);
                return;
            }
            System.out.println("The details of the guest you selected are as follows:");
            viewGuestRow(guestID);
            System.out.println();
            requestGuestData(guestID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }
    
    public static void requestGuestData(int guestID) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestID(guestID);
        guest.setTitle(UserInput.reqString("Enter the guest's title"));
        guest.setFirstName(UserInput.reqString("Enter the guest's first name"));
        guest.setLastName(UserInput.reqString("Enter the guest's last name"));
        guest.setAddress(UserInput.reqString("Enter the guest's address"));
        guest.setEmail(UserInput.reqString("Enter the guest's email"));
        guest.setPhone(UserInput.reqString("Enter the guest's phone number"));
        boolean updateGuest = updateGuest(guest);
        if(updateGuest){
            System.out.println("\nThe guest with the primary key of " + guest.getGuestID() + " was successfully updated.");
        }
    }
    
    public static boolean updateGuest(Guest guest) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEGUEST);) {
            
            stmt.setString(1, guest.getTitle());
            stmt.setString(2, guest.getFirstName());
            stmt.setString(3, guest.getLastName());
            stmt.setString(4, guest.getAddress());
            stmt.setString(5, guest.getEmail());
            stmt.setString(6, guest.getPhone());
            stmt.setInt(7, guest.getGuestID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the guest failed.");
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
    public static void deleteGuest(int guestID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEGUEST);) {
            
            stmt.setInt(1, guestID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe guest with the primary key of " + guestID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
}
