package me.shastri.hotelaccommodation.util;

import me.shastri.hotelaccommodation.beans.*;
import me.shastri.hotelaccommodation.controllers.*;
import me.shastri.libs.UserInput;
import java.sql.SQLException;

public class AppMenu {
    /*
    * Create
    */
    public static void CreateMenu() throws SQLException {
        int response = DBManager.dbMenu("add");
        switch (response) {
            case 1:
                BookingController.requestNewBookingData();
                break;
            case 2:
                GuestController.requestNewGuestData();
                break;
            case 3:
                GuestBookingController.requestNewGuestBookingData();
                break;
            case 4:
                HotelController.requestNewHotelData();
                break;
            case 5:
                ManagerController.requestNewManagerData();
                break;
            case 6:
                RoomController.requestNewRoomData();
                break;
            default:
                break;
        }
    }
    
    
    /*
    * Read
    */
    public static void ReadMenu() throws SQLException {
        int response = DBManager.dbMenu("view");
        System.out.println();
        switch (response) {
            case 1:
                BookingReadMenu();
                break;
            case 2:
                GuestReadMenu();
                break;
            case 3:
                GuestBookingReadMenu();
                break;
            case 4:
                HotelReadMenu();
                break;
            case 5:
                ManagerReadMenu();
                break;
            case 6:
                RoomReadMenu();
                break;
            default:
                break;
        }
    }
    
    public static void BookingReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single booking."
                    + "\n"
                    + "2. View all bookings."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int bookingID = UserInput.reqInt("\nPlease enter the ID of the booking you want to view below.\nBooking ID");
                Booking booking = BookingController.getBookingRow(bookingID);
                System.out.println(booking);
                break;
            case 2:
                BookingController.viewTableContents();
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
    
    public static void GuestBookingReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single guest-booking."
                    + "\n"
                    + "2. View all guest-bookings."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int guestBookingID = UserInput.reqInt("\nPlease enter the ID of the guest-BOOKING you want to view below.\nGuestBooking ID");
                GuestBooking guestBooking = GuestBookingController.getGuestBookingRow(guestBookingID);
                System.out.println(guestBooking);
                break;
            case 2:
                GuestBookingController.viewTableContents();
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
    
    public static void RoomReadMenu() throws SQLException {
        int response = 0;
        try {
            response = UserInput.reqInt("Choose one of the following options."
                    + "\n\n"
                    + "1. View a single room."
                    + "\n"
                    + "2. View all rooms."
                    + "\n\n"
                    + "Please make your selection by entering the corresponding number");
        } catch (NumberFormatException e) {
            System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                    + "Please enter a number.");
        }
        switch (response) {
            case 1:
                int roomID = UserInput.reqInt("\nPlease enter the ID of the room you want to view below.\nRoom ID");
                Room room = RoomController.getRoomRow(roomID);
                System.out.println(room);
                break;
            case 2:
                RoomController.viewTableContents();
                break;
            default:
                break;
        }
    }
    
    
    /*
    * Update
    */
    public static void UpdateMenu() throws SQLException {
        int response = DBManager.dbMenu("modify");
        switch (response) {
            case 1:
                BookingController.updateBookingHandler();
                break;
            case 2:
                GuestController.updateGuestHandler();
                break;
            case 3:
                GuestBookingController.updateGuestBookingHandler();
                break;
            case 4:
                HotelController.updateHotelHandler();
                break;
            case 5:
                ManagerController.updateManagerHandler();
                break;
            case 6:
                RoomController.updateRoomHandler();
                break;
            default:
                break;
        }
    }
    
    
    /*
    * Delete
    */
    public static void DeleteMenu() throws SQLException {
        int response = DBManager.dbMenu("delete");
        switch (response) {
            case 1:
                int bookingID = UserInput.reqInt("\nPlease enter the ID of the booking you want to delete below.\nBooking ID");
                BookingController.deleteBooking(bookingID);
                break;
            case 2:
                int guestID = UserInput.reqInt("\nPlease enter the ID of the guest you want to delete below.\nGuest ID");
                GuestController.deleteGuest(guestID);
                break;
            case 3:
                int guestBookingID = UserInput.reqInt("\nPlease enter the ID of the guest-booking you want to delete below.\nGuestBooking ID");
                GuestBookingController.deleteGuestBooking(guestBookingID);
                break;
            case 4:
                int hotelID = UserInput.reqInt("\nPlease enter the ID of the hotel you want to delete below.\nHotel ID");
                HotelController.deleteHotel(hotelID);
                break;
            case 5:
                int managerID = UserInput.reqInt("\nPlease enter the ID of the manager you want to delete below.\nManager ID");
                ManagerController.deleteManager(managerID);
                break;
            case 6:
                int roomID = UserInput.reqInt("\nPlease enter the ID of the room you want to delete below.\nRoom ID");
                RoomController.deleteRoom(roomID);
                break;
            default:
                break;
        }
    }
}
