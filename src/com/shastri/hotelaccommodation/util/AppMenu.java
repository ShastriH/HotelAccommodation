package com.shastri.hotelaccommodation.util;

import com.shastri.hotelaccommodation.beans.*;
import com.shastri.hotelaccommodation.controllers.*;
import me.shastri.libs.UserInput;
import java.sql.SQLException;

public class AppMenu {
    public static void CreateMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Which type of record would you like to add?"
                    + "\n\n"
                    + "1. Guest"
                    + "\n"
                    + "2. Booking"
                    + "\n"
                    + "3. Room"
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                GuestController.requestNewGuestData();
                break;
            default:
                break;
        }
    }

    public static void ReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Which type of record would you like to view?"
                    + "\n\n"
                    + "1. Booking"
                    + "\n"
                    + "2. Guest"
                    + "\n"
                    + "3. Hotel"
                    + "\n"
                    + "4. Room"
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        System.out.println();
        switch (response) {
            case 1:
                break;
            case 2:
                GuestReadMenu();
                break;
            case 3:
                HotelReadMenu();
                break;
            case 4:
                break;
            default:
                break;
        }
    }
    
    public static void GuestReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single guest."
                    + "\n"
                    + "2. View all guests."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int guestID = UserInput.reqInt("\nPlease enter the ID of the guest you want to view below.\nGuest ID");
                Guest guest = GuestController.getRow(guestID);
                System.out.println(guest);
                break;
            case 2:
                GuestController.viewTableContents();
                break;
            default:
                break;
        }
    }
    
    public static void HotelReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single hotel."
                    + "\n"
                    + "2. View all hotels."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int hotelID = UserInput.reqInt("\nPlease enter the ID of the hotel you want to view below.\nHotel ID");
                Hotel hotel = HotelController.getRow(hotelID);
                System.out.println(hotel);
                break;
            case 2:
                HotelController.viewTableContents();
                break;
            default:
                break;
        }
    }
    
    public static void UpdateMenu() throws SQLException {
        int response;
        try {
            response = UserInput.reqInt("Which type of record would you like to modify?" + "\n\n" + "1. Guest" + "\n" + "2. Booking" + "\n" + "3. Room" + "\n\n" + "Please make your selection by entering the corresponding number");
            switch (response) {
                case 1:
                    System.out.println();
                    GuestController.updateGuestHandler();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
    }
       
    public static void DeleteMenu() throws SQLException {
        int response;
        try {
            response = UserInput.reqInt("Which type of record would you like to delete?" + "\n\n" + "1. Guest" + "\n" + "2. Booking" + "\n" + "3. Room" + "\n\n" + "Please make your selection by entering the corresponding number");
            switch (response) {
                case 1:
                    int guestID = UserInput.reqInt("\nPlease enter the ID of the guest you want to delete below.\nGuest ID");
                    GuestController.deleteGuest(guestID);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
    }
}
