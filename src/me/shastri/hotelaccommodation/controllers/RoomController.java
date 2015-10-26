package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.shastri.libs.UserInput;

public class RoomController {
    private static Connection conn = DBManager.getInstance().getConnection();
    private static Boolean getBoolean(String seaView){
        return !seaView.equals("0");
    }
    /*
    * Create
    */
    public static void requestNewRoomData() throws SQLException {
        Room room = new Room();
        room.setPrice(UserInput.reqDouble("Enter the room's price"));
        room.setCapacity(UserInput.reqInt("Enter the room's capacity"));
        room.setSeaView(getBoolean(UserInput.reqString("Does the room has a sea view (enter 0 for no and 1 for yes)")));
        room.setHotelID(UserInput.reqInt("Enter the hotel ID"));
        boolean insertRoom = insertRoom(room);
        if (insertRoom) {
            System.out.println("A new row with the primary key of " + room.getRoomID() + " was successfully inserted.");
        }
    }
    
    public static boolean insertRoom(Room room) throws SQLException {
        ResultSet key = null;
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTROOM, Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setDouble(1, room.getPrice());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.getSeaView());
            stmt.setInt(4, room.getHotelID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                room.setRoomID(key.getInt(1));
            } else {
                System.err.println("The attempt to insert the room failed.");
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
    public static void viewRoomRow(int roomID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMROW);){
            stmt.setInt(1, roomID);
            result = stmt.executeQuery();
            
            if(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("roomID: ").append(roomID).append("\n");
                rowData.append("Price: ").append(result.getDouble("price")).append("\n");
                rowData.append("Capacity: ").append(result.getInt("capacity")).append("\n");
                rowData.append("Sea View: ").append(result.getBoolean("seaView")).append("\n");
                rowData.append("hotelID: ").append(result.getInt("hotelID")).append("\n");
                System.out.print(rowData);
            } else {
                System.err.println("No room was found with an ID of " + roomID);
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
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOM);){
            result = stmt.executeQuery();
            System.out.println("The contents of the room table are as follows: " + "\n");
            while(result.next()){
                StringBuffer rowData = new StringBuffer();
                rowData.append("roomID: ").append(result.getDouble("roomID")).append("\n");
                rowData.append("Price: ").append(result.getDouble("price")).append("\n");
                rowData.append("Capacity: ").append(result.getInt("capacity")).append("\n");
                rowData.append("Sea View: ").append(result.getBoolean("seaView")).append("\n");
                rowData.append("hotelID: ").append(result.getInt("hotelID")).append("\n");
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
    
    public static Room getRoomRow(int roomID) throws SQLException {
        ResultSet result = null;
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMROW);){
            stmt.setInt(1, roomID);
            result = stmt.executeQuery();
            
            if(result.next()){
                Room room = new Room(
                        roomID,
                        result.getDouble("price"),
                        result.getInt("capacity"),
                        result.getBoolean("seaView"),
                        result.getInt("hotelID")
                );
                return room;
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
    public static void updateRoomHandler() throws SQLException {
        try {
            int roomID = UserInput.reqInt("Please enter the ID of the room you want to modify below.\nRoom ID");
            System.out.println();
            Room room = getRoomRow(roomID);
            if (room == null) {
                System.err.println("No room was found with an ID of " + roomID);
                return;
            }
            System.out.println("The details of the room you selected are as follows:");
            viewRoomRow(roomID);
            System.out.println();
            requestRoomData(roomID);
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n" + "Please enter a number.");
        }
    }
    
    public static void requestRoomData(int roomID) throws SQLException {
        Room room = new Room();
        room.setRoomID(roomID);
        room.setPrice(UserInput.reqDouble("Enter the room's price"));
        room.setCapacity(UserInput.reqInt("Enter the room's capacity"));
        room.setSeaView(getBoolean(UserInput.reqString("Does the room has a sea view (enter 0 for no and 1 for yes)")));
        room.setHotelID(UserInput.reqInt("Enter the hotel ID"));
        boolean updateRoom = updateRoom(room);
        if(updateRoom){
            System.out.println("\nThe room with the primary key of " + room.getRoomID() + " was successfully updated.");
        }
    }
    
    public static boolean updateRoom(Room room) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEROOM);) {
            
            stmt.setDouble(1, room.getPrice());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.getSeaView());
            stmt.setInt(4, room.getHotelID());
            stmt.setInt(5, room.getRoomID());
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                return true;
            } else {
                System.err.println("The attempt to update the room failed.");
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
    public static void deleteRoom(int roomID) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEROOM);) {
            
            stmt.setInt(1, roomID);
            
            int numAffectedRows = stmt.executeUpdate();
            if(numAffectedRows == 1){
                System.out.println("\nThe room with the primary key of " + roomID + " was successfully deleted.");
            } else {
                System.err.println("The requested record was not found. Nothing was deleted.");
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
}
