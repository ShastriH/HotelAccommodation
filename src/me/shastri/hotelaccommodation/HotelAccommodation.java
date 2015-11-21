/**
 * 
 * Author:              Shastri Harrinanan
 * Version:             0.6.0.0
 * Project Revision:    #012
 * Date Created:        22/10/2015
 * Date Modified:       21/11/2015
 * 
 * Commit Log:
 * #001 22/10/2015 0.0.0.0 - 0.1.0.0: Add initial files
 * #002 23/10/2015 0.1.0.0 - 0.1.1.0: Reorganise the main menu and add missing constructors
 * #003 23/10/2015 0.1.1.0 - 0.2.0.0: Add partial create functionality and refactor the menus
 * #004 23/10/2015 0.2.0.0 - 0.2.1.0: Add partial update functionality
 * #005 23/10/2015 0.2.1.0 - 0.2.2.0: Add partial delete functionality
 * #006 23/10/2015 0.2.2.0 - 0.3.0.0: Change to a persistent database connection
 * #007 24/10/2015 0.3.0.0 - 0.3.1.0: Add complete functionality for more tables
 * #008 25/10/2015 0.3.1.0 - 0.4.0.0: Add complete functionality for the remaining tables
 * #009 26/10/2015 0.4.0.0 - 0.5.0.0: Convert project to GUI
 * #010 26/10/2015 0.5.0.0 - 0.5.0.1: Conduct minor cleaning
 * #011 27/10/2015 0.5.0.1 - 0.5.1.0: Add remaining functionality for one table
 * #012 21/11/2015 0.5.1.0 - 0.6.0.0: Convert the application from a CLI to a GUI version
 * 
 * Current Status: Under Construction.
 * 
 */

package me.shastri.hotelaccommodation;

import me.shastri.hotelaccommodation.util.AppMenu;
import me.shastri.hotelaccommodation.util.DBManager;
import java.sql.SQLException;
import me.shastri.libs.UserInput;

public class HotelAccommodation {

    public static void main_HotelAccommodation(String[] args) throws SQLException {
        DBManager.getInstance();
        System.out.println("Welcome to the Hotel Accommodation App!\n\n");
        int response = 0;
        do{
            try {
                response = UserInput.reqInt("Which operation would you like to perform on the database?"
                        + "\n\n"
                        + "1. Add a record"
                        + "\n"
                        + "2. View a record"
                        + "\n"
                        + "3. Update a record"
                        + "\n"
                        + "4. Delete a record"
                        + "\n"
                        + "5. Exit HotelAccommodation"
                        + "\n\n"
                        + "Please make your selection by entering the corresponding number");
            System.out.println();
            switch(response){
                case 1:
                    // Create
                    AppMenu.CreateMenu();
                    break;
                case 2:
                    // Read
                    AppMenu.ReadMenu();
                    break;
                case 3:
                    // Update
                    AppMenu.UpdateMenu();
                    break;
                case 4:
                    // Delete
                    AppMenu.DeleteMenu();
                    break;
                case 5:
                    // Exit
                    break;
                default:
                    // Process any unknown values
                    System.out.println("You entered an invalid value. Please try again.");
                    break;
            }
            } catch (NumberFormatException e) {
                System.out.println("\nAn error occurred. You did not enter a valid value.\n"
                        + "Please enter a number.");
            }
            System.out.println("\n");
        } while(response != 5);
        
        // Close the connection when the application terminates
        DBManager.getInstance().close();
    }
}
