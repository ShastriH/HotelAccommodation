package com.shastri.hotelaccommodation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
    private static final String USERNAME = "hoteluser";
    private static final String PASSWORD = "pass";
    private static final String CONN_STRING = "jdbc:mysql://localhost/hotelaccommodationdb";

    /*
    *   SQL queries
    */
    // SQL queries for the hotel table
    public static final String HOTEL = "SELECT * FROM hotel";
    public static final String HOTELROW = "SELECT * FROM hotel WHERE hotelID = ?";
    
    // SQL queries for the guest table
    // Create
    public static final String INSERTGUEST = "INSERT into guest "
            + "(guestTitle, guestFirstName, guestLastName, guestAddress, guestEmail, guestPhone) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    // Read
    public static final String GUEST = "SELECT * FROM guest";
    public static final String GUESTROW = "SELECT * FROM guest WHERE guestID = ?";
    // Update
    public static final String UPDATEGUEST = "UPDATE guest SET "
            + "guestTitle = ?, guestFirstName = ?, guestLastName = ?, guestAddress = ?, guestEmail = ?, guestPhone = ? "
            + "WHERE guestID = ?";
    // Delete
    
    
    /*
    *   Utility methods
    */
   public static Connection connect() throws SQLException {
       return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
   }
    
}