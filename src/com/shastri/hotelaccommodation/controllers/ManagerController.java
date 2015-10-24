package com.shastri.hotelaccommodation.controllers;

import com.shastri.hotelaccommodation.beans.Manager;
import com.shastri.hotelaccommodation.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;

public class ManagerController {
    private static Connection conn = DBManager.getInstance().getConnection();
    /*
    * Create
    */
    public static void requestNewManagerData() throws SQLException {
        Manager manager = new Manager();
        manager.setFirstName(UserInput.reqString("Enter the manager's first name"));
        manager.setLastName(UserInput.reqString("Enter the manager's last name"));
        manager.setEmail(UserInput.reqString("Enter the manager's email"));
        manager.setPhone(UserInput.reqString("Enter the manager's phone number"));
        manager.setHotelID(UserInput.reqInt("Enter the hotel ID"));
        boolean insertManager = insertManager(manager);
        if (insertManager) {
            System.out.println("A new row with the primary key of " + manager.getManagerID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertManager(Manager manager) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTMANAGER, Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setString(1, manager.getFirstName());
            stmt.setString(2, manager.getLastName());
            stmt.setString(3, manager.getEmail());
            stmt.setString(4, manager.getPhone());
            stmt.setInt(5, manager.getHotelID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                manager.setManagerID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the manager failed.");
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
    public static void viewManagerRow(int managerID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.MANAGERROW);){
            stmt.setInt(1, managerID);
            result = stmt.executeQuery();
            
            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("First name: ").append(result.getString("managerFirstName")).append("\n");
                rowData.append("Last name: ").append(result.getString("managerLastName")).append("\n");
                rowData.append("Email: ").append(result.getString("managerEmail")).append("\n");
                rowData.append("Telephone number: ").append(result.getString("managerPhone")).append("\n");
                rowData.append("Hotel ID: ").append(result.getInt("hotelID")).append("\n");
                System.out.print(rowData);
            } else {
                System.err.println("No manager was found with an ID of " + managerID);
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
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.MANAGER);){
            result = stmt.executeQuery();
            System.out.println("The contents of the manager table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("Manager ID: ").append(result.getString("managerID")).append("\n");
                rowData.append("First name: ").append(result.getString("managerFirstName")).append("\n");
                rowData.append("Last name: ").append(result.getString("managerLastName")).append("\n");
                rowData.append("Email: ").append(result.getString("managerEmail")).append("\n");
                rowData.append("Telephone number: ").append(result.getString("managerPhone")).append("\n");
                rowData.append("Hotel ID: ").append(result.getInt("hotelID")).append("\n");
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
    public static Manager getManagerRow(int managerID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.MANAGERROW);){
            stmt.setInt(1, managerID);
            result = stmt.executeQuery();
            
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
    public static void updateManagerHandler() throws SQLException {
        try {
            int managerID = UserInput.reqInt("Please enter the ID of the manager you want to modify below.\nManager ID");
            System.out.println();
            Manager manager = getManagerRow(managerID);
            if (manager == null) {
                System.err.println("No manager was found with an ID of " + managerID);
                return;
            }
            System.out.println("The details of the manager you selected are as follows:");
            viewManagerRow(managerID);
            System.out.println();
            requestManagerData(managerID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }
    
    public static void requestManagerData(int managerID) throws SQLException {
        Manager manager = new Manager();
        manager.setManagerID(managerID);
        manager.setFirstName(UserInput.reqString("Enter the manager's first name"));
        manager.setLastName(UserInput.reqString("Enter the manager's last name"));
        manager.setEmail(UserInput.reqString("Enter the manager's email"));
        manager.setPhone(UserInput.reqString("Enter the manager's phone number"));
        manager.setHotelID(UserInput.reqInt("Enter the hotel ID"));
        boolean updateManager = updateManager(manager);
        if(updateManager){
            System.out.println("\nThe manager with the primary key of " + manager.getManagerID() + " was successfully updated.");
        }
    }
    
    public static boolean updateManager(Manager manager) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEMANAGER);) {
            
            stmt.setString(1, manager.getFirstName());
            stmt.setString(2, manager.getLastName());
            stmt.setString(3, manager.getEmail());
            stmt.setString(4, manager.getPhone());
            stmt.setInt(5, manager.getHotelID());
            stmt.setInt(6, manager.getManagerID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the manager failed.");
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
    public static void deleteManager(int managerID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEMANAGER);) {
            
            stmt.setInt(1, managerID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe manager with the primary key of " + managerID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    } 
}
