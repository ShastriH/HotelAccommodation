package me.shastri.hotelaccommodation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import me.shastri.libs.UserInput;

/**
 *
 * @author Shastri
 */
public class DBManager {
    
    private static DBManager instance = null;

    private static final String USERNAME = "hoteluser";
    private static final String PASSWORD = "pass";
    private static final String CONN_STRING = "jdbc:mysql://localhost/hotelaccommodationdb";

    private Connection conn = null;

    private DBManager() {}

    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private void openConnection(){
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getConnection(){
        if(conn == null){
            openConnection();
            return conn;
        }
        return conn;
    }

    public void close() {
        try {
            // Check if the connection was never opened before attempting to close it
            if(conn != null){
                conn.close();
                conn = null;
            }
        } catch(SQLException e){
            System.out.println(e);
        }
    }

    /*
    *   SQL queries
    */
    // SQL queries for the booking table    
    // Create
    public static final String INSERTBOOKING = "INSERT into booking (guestID, arrivalDate, departureDate) VALUES (?, ?, ?)";
    // Read
    public static final String BOOKING = "SELECT * FROM booking";
    public static final String BOOKINGID = "SELECT bookingID FROM booking";
    public static final String BOOKINGROW = "SELECT * FROM booking WHERE bookingID = ?";
    // Update
    public static final String UPDATEBOOKING = "UPDATE booking SET guestID = ?, arrivalDate = ?, departureDate = ? WHERE bookingID = ?";
    // Delete
    public static final String DELETEBOOKING = "DELETE FROM booking WHERE bookingID = ?";
    
    // SQL queries for the guest table
    // Create
    public static final String INSERTGUEST = "INSERT into guest "
            + "(guestTitle, guestFirstName, guestLastName, guestAddress, guestEmail, guestPhone) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    // Read
    public static final String GUEST = "SELECT * FROM guest";
    public static final String GUESTID = "SELECT guestID FROM guest";
    public static final String GUESTROW = "SELECT * FROM guest WHERE guestID = ?";
    // Update
    public static final String UPDATEGUEST = "UPDATE guest SET "
            + "guestTitle = ?, guestFirstName = ?, guestLastName = ?, guestAddress = ?, guestEmail = ?, guestPhone = ? "
            + "WHERE guestID = ?";
    // Delete
    public static final String DELETEGUEST = "DELETE FROM guest WHERE guestID = ?";
    
    // SQL queries for the hotel table    
    // Create
    public static final String INSERTHOTEL = "INSERT into hotel "
            + "(hotelAddress) "
            + "VALUES (?)";
    // Read
    public static final String HOTEL = "SELECT * FROM hotel";
    public static final String HOTELID = "SELECT hotelID FROM hotel";
    public static final String HOTELROW = "SELECT * FROM hotel WHERE hotelID = ?";
    // Update
    public static final String UPDATEHOTEL = "UPDATE hotel SET "
            + "hotelAddress = ? "
            + "WHERE hotelID = ?";
    // Delete
    public static final String DELETEHOTEL = "DELETE FROM hotel WHERE hotelID = ?";
    
    // SQL queries for the manager table
    // Create
    public static final String INSERTMANAGER = "INSERT into manager "
            + "(managerFirstName, managerLastName, managerEmail, managerPhone, hotelID) "
            + "VALUES (?, ?, ?, ?, ?)";
    // Read
    public static final String MANAGER = "SELECT * FROM manager";
    public static final String MANAGERID = "SELECT managerID FROM manager";
    public static final String MANAGERROW = "SELECT * FROM manager WHERE managerID = ?";
    // Update
    public static final String UPDATEMANAGER = "UPDATE manager SET "
            + "managerFirstName = ?, managerLastName = ?, managerEmail = ?, managerPhone = ?, hotelID = ? "
            + "WHERE managerID = ?";
    // Delete
    public static final String DELETEMANAGER = "DELETE FROM manager WHERE managerID = ?";
    
    // SQL queries for the room table
    // Create
    public static final String INSERTROOM = "INSERT into room "
            + "(price, capacity, seaView, hotelID) "
            + "VALUES (?, ?, ?, ?)";
    // Read
    public static final String ROOM = "SELECT * FROM room";
    public static final String ROOMID = "SELECT roomID FROM room";
    public static final String ROOMROW = "SELECT * FROM room WHERE roomID = ?";
    // Update
    public static final String UPDATEROOM = "UPDATE room SET "
            + "price = ?, capacity = ?, seaView = ?, hotelID = ? "
            + "WHERE roomID = ?";
    // Delete
    public static final String DELETEROOM = "DELETE FROM room WHERE roomID = ?";
    
    // SQL queries for the room-booking table
    // Create
    public static final String INSERTROOMBOOKING = "INSERT into room_booking "
            + "(roomID, bookingID) "
            + "VALUES (?, ?)";
    // Read
    public static final String ROOMBOOKING = "SELECT * FROM room_booking";
    public static final String ROOMBOOKINGID = "SELECT roomBookingID FROM room_booking";
    public static final String ROOMBOOKINGROW = "SELECT * FROM room_booking WHERE roomBookingID = ?";
    public static final String ROOMBOOKINGROOMS = "SELECT roomID FROM room_booking WHERE bookingID = ?";
    // Update
    public static final String UPDATEROOMBOOKING = "UPDATE room_booking SET roomID = ? WHERE bookingID = ?";
    // Delete
    public static final String DELETEROOMBOOKING = "DELETE FROM room_booking WHERE roomBookingID = ?";
    public static final String DELETEROOMBOOKINGS = "DELETE FROM room_booking WHERE bookingID = ?";
    
    
    /*
    *   Utility methods
    */
   public static Connection connect() throws SQLException {
       return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
   }
   public static int dbMenu(String action){
       try {
            return UserInput.reqInt("Which type of record would you like to " + action + "?"
                + "\n\n"
                + "1. Booking"
                + "\n"
                + "2. Guest"
                + "\n"
                + "3. Hotel"
                + "\n"
                + "4. Manager"
                + "\n"
                + "5. Room"
                + "\n"
                + "6. Room-Booking"
                + "\n"
                + "7. Cancel"
                + "\n\n"
                + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
       return 0;
   }
}
