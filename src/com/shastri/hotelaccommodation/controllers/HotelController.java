package com.shastri.hotelaccommodation.controllers;

// @author Shastri

import com.shastri.hotelaccommodation.beans.Hotel;
import com.shastri.hotelaccommodation.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HotelController {
    public static void getTableContents() throws SQLException{
        ResultSet result = null;
        try (
                Connection conn = DBUtil.connect();
                PreparedStatement stmt = conn.prepareStatement(DBUtil.HOTEL);
            ){
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
    public static Hotel getRow(int hotelID) throws SQLException {
        ResultSet result = null;
        try (
                Connection conn = DBUtil.connect();
                PreparedStatement stmt = conn.prepareStatement(DBUtil.HOTELROW);
            ){
            stmt.setInt(1, hotelID);
            result = stmt.executeQuery();
            
            if(result.next()){
                Hotel guest = new Hotel(
                        result.getInt("hotelID"),
                        result.getString("hotelAddress")
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
}