package me.shastri.hotelaccommodation.controllers;

import me.shastri.hotelaccommodation.util.DBManager;
import me.shastri.hotelaccommodation.beans.Room;
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
public class RoomController {
    private static Connection conn = DBManager.getInstance().getConnection();

    /*
    * Create Method
    */

    /**
     * This method executes the insert of a room into the database using a Room object's fields as the values for the SQL statement.
     *
     * @param room  The room object containing the data to be inserted into the database as a new row in the room table
     * @return  The indication of whether the method succeeded or failed to add a room to the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean insertRoom(Room room) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet key = null;
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.INSERTROOM, Statement.RETURN_GENERATED_KEYS);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure INSERTROOM
            stmt.setDouble(1, room.getPrice());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.getSeaView());
            stmt.setInt(4, room.getHotelID());
            // Execute the query and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the insert was successful, one row would have been affected
            if(numAffectedRows == 1){
                // Acquire the generated keys
                key = stmt.getGeneratedKeys();
                // Move the cursor to the first (and only) key
                key.next();
                // Assign the value of the new row's ID (room ID) as the Room object's room ID
                room.setRoomID(key.getInt(1));
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to insert the room failed.");
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
     * This method retrieves a row from the room table, creates a Room object using that data and returns it.
     * @param roomID    The integer referring to the ID of the requested room
     * @return  The requested guest as a Room object
     * @throws SQLException     if a database error occurs
     */
    public static Room getRoomRow(int roomID) throws SQLException {
        // Declare the ResultSet here to be able to work with it within the try block
        ResultSet result = null;
        // Prepare the statement
        try (PreparedStatement stmt = conn.prepareStatement(DBManager.ROOMROW);){
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure ROOMROW
            stmt.setInt(1, roomID);
            // Execute the query
            result = stmt.executeQuery();
            // If the query successfully retrieved the room record from the guest table, then use it to create a Room object
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
     * This method accepts a Room object and uses its fields as the values to be used to update a row in the room table in the database.
     *
     *
     * @param room  The room object containing the data to be updated
     * @return  The indication of whether the method succeeded or failed to update a room in the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean updateRoom(Room room) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.UPDATEROOM);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure UPDATEROOM
            stmt.setDouble(1, room.getPrice());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.getSeaView());
            stmt.setInt(4, room.getHotelID());
            stmt.setInt(5, room.getRoomID());
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                return true;
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The attempt to update the room failed.");
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
     * This method deletes a row from the room table in the database corresponding to the specified room ID.
     *
     * @param roomID    The integer referring to the ID of the room record to be deleted
     * @return  The indication of whether the method succeeded or failed to remove a room from the database
     * @throws SQLException     if a database error occurs
     */
    public static boolean deleteRoom(int roomID) throws SQLException {
        // Prepare the statement
        try(PreparedStatement stmt = conn.prepareStatement(DBManager.DELETEROOM);) {
            // Complete the preparation of the SQL statement by assigning the values for the stored procedure DELETEROOM
            stmt.setInt(1, roomID);
            // Execute the delete and store the number of rows of the database which were affected
            int numAffectedRows = stmt.executeUpdate();
            // If the delete was successful, one row would have been affected
            if(numAffectedRows == 1){
                JOptionPane.showMessageDialog(null, "\nThe room with the primary key of " + roomID + " was successfully deleted.");
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

    private static Boolean getBoolean(String seaView){
        return !seaView.equals("0");
    }
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
                System.out.println("No room was found with an ID of " + roomID);
            }
        } catch(SQLException e) {
            System.out.println(e);
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
            System.out.println(e);
        } finally {
            if(result != null){
                result.close();
            }
        }
    }

    public static void updateRoomHandler() throws SQLException {
        try {
            int roomID = UserInput.reqInt("Please enter the ID of the room you want to modify below.\nRoom ID");
            System.out.println();
            Room room = getRoomRow(roomID);
            if (room == null) {
                System.out.println("No room was found with an ID of " + roomID);
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


}
