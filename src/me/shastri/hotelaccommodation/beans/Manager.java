package me.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri
 */
public class Manager {
    private int managerID = 0;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private int hotelID = 0;

    /**
     * No arguments constructor.
     */
    public Manager(){}

    /**
     * This is the constructor used to create a Manager object using data provided by
     * the user which is to be inserted as a new row into the manager table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param firstName     The first name of the manager
     * @param lastName      The last name of the manager
     * @param email         The email address of the manager
     * @param phone         The telephone number of the manager
     * @param hotelID       The ID of the hotel in which the manager worked
     */
    public Manager(String firstName, String lastName, String email, String phone, int hotelID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hotelID = hotelID;
    }

    /**
     * This is the constructor used to create a complete Manager object.
     * @param managerID     The ID of the manager record in the manager table in the database
     * @param firstName     The first name of the manager
     * @param lastName      The last name of the manager
     * @param email         The email address of the manager
     * @param phone         The telephone number of the manager
     * @param hotelID       The ID of the hotel in which the manager worked
     */
    public Manager(int managerID, String firstName, String lastName, String email, String phone, int hotelID){
        this.managerID = managerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hotelID = hotelID;
    }

    /**
     * The getter for the ID of the manager record.
     * @return  The ID of the manager record
     */
    public int getManagerID(){
        return managerID;
    }

    /**
     * The setter for the ID of the manager record.
     * @param managerID     The ID of the manager record
     */
    public void setManagerID(int managerID){
        this.managerID = managerID;
    }

    /**
     * The getter for the manager's first name.
     * @return  The manager's first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * The setter for the manager's first name.
     * @param firstName     The manager's first name
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * The getter for the manager's last name.
     * @return  The manager's last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * The setter for the manager's last name.
     * @param lastName  The manager's last name
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * The getter for the manager's email address.
     * @return  The manager's email address
     */
    public String getEmail(){
        return email;
    }

    /**
     * The setter for the manager's email address.
     * @param email     The manager's email address.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * The getter for the manager's telephone number.
     * @return The manager's telephone number
     */
    public String getPhone(){
        return phone;
    }

    /**
     * The setter for the manager's telephone number.
     * @param phone     The manager's telephone number
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * The getter for the ID of the hotel in which the manager worked.
     * @return The ID of the hotel in which the manager worked
     */
    public int getHotelID() {
        return hotelID;
    }

    /**
     * The setter for the ID of the hotel in which the manager worked.
     * @param hotelID   The ID of the hotel in which the manager worked
     */
    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return  The full name of the manager
     */
    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}
