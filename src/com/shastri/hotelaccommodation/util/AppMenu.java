package com.shastri.hotelaccommodation.util;

import com.shastri.hotelaccommodation.beans.*;
import com.shastri.hotelaccommodation.controllers.*;
import me.shastri.libs.UserInput;
import java.sql.SQLException;

public class AppMenu {
    /*
    * Create
    */
    public static void CreateMenu() throws SQLException {
        int response = 0;
        try {
            response = DBManager.dbMenu("add");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                break;
            case 2:
                GuestController.requestNewGuestData();
                break;
            case 3:
                HotelController.requestNewHotelData();
                break;
            case 4:
                ManagerController.requestNewManagerData();
                break;
            case 5:
                break;
            default:
                break;
        }
    }
    
    
    /*
    * Read
    */
    public static void ReadMenu() throws SQLException {
        int response = 0;
        try {
            response = DBManager.dbMenu("view");
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
                ManagerReadMenu();
                break;
            case 5:
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
                Guest guest = GuestController.getGuestRow(guestID);
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
                Hotel hotel = HotelController.getHotelRow(hotelID);
                System.out.println(hotel);
                break;
            case 2:
                HotelController.viewTableContents();
                break;
            default:
                break;
        }
    }
    
    public static void ManagerReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single manager."
                    + "\n"
                    + "2. View all managers."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int managerID = UserInput.reqInt("\nPlease enter the ID of the manager you want to view below.\nManager ID");
                Manager manager = ManagerController.getManagerRow(managerID);
                System.out.println(manager);
                break;
            case 2:
                ManagerController.viewTableContents();
                break;
            default:
                break;
        }
    }
    
    
    /*
    * Update
    */
    public static void UpdateMenu() throws SQLException {
        int response;
        try {
            response = DBManager.dbMenu("modify");
            System.out.println();
            switch (response) {
                case 1:
                    break;
                case 2:
                    GuestController.updateGuestHandler();
                    break;
                case 3:
                    HotelController.updateHotelHandler();
                    break;
                case 4:
                    ManagerController.updateManagerHandler();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
    }
    
    
    /*
    * Delete
    */
    public static void DeleteMenu() throws SQLException {
        int response;
        try {
            response = DBManager.dbMenu("delete");
            switch (response) {
                case 1:
                    break;
                case 2:
                    int guestID = UserInput.reqInt("\nPlease enter the ID of the guest you want to delete below.\nGuest ID");
                    GuestController.deleteGuest(guestID);
                    break;
                case 3:
                    int hotelID = UserInput.reqInt("\nPlease enter the ID of the hotel you want to delete below.\nHotel ID");
                    HotelController.deleteHotel(hotelID);
                    break;
                case 4:
                    int managerID = UserInput.reqInt("\nPlease enter the ID of the manager you want to delete below.\nManager ID");
                    ManagerController.deleteManager(managerID);
                    break;
                case 5:
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
