package me.shastri.hotelaccommodation.beans;

/**
 *
 * @author Shastri Harrinanan
 */
public class Guest {
    private int guestID = 0;
    private String title = "";
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String email  = "";
    private String phone = "";

    /**
     * No arguments constructor.
     */
    public Guest() {}

    /**
     * This is the constructor used to create a Guest object using data provided by
     * the user which is to be inserted as a new row into the guest table in the
     * database. The ID is not used because it is handled automatically by the database.
     * @param title         The title of the guest
     * @param firstName     The first name of the guest
     * @param lastName      The last name of the guest
     * @param address       The address of the guest
     * @param email         The email address of the guest
     * @param phone         The telephone number of the guest
     */
    public Guest(String title, String firstName, String lastName, String address, String email, String phone){
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * This is the constructor used to create a complete Guest object.
     * @param guestID       The ID of the guest record in the guest table in the database
     * @param title         The title of the guest
     * @param firstName     The first name of the guest
     * @param lastName      The last name of the guest
     * @param address       The address of the guest
     * @param email         The email address of the guest
     * @param phone         The telephone number of the guest
     */
    public Guest(int guestID, String title, String firstName, String lastName, String address, String email, String phone){
        this.guestID = guestID;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * The getter for the ID of the guest record.
     * @return  The ID of the guest record
     */
    public int getGuestID(){
        return guestID;
    }

    /**
     * The setter for the ID of the guest record.
     * @param guestID   The ID of the guest record
     */
    public void setGuestID(int guestID){
        this.guestID = guestID;
    }

    /**
     * The getter for the guest's title.
     * @return  The title of the guest
     */
    public String getTitle(){
        return title;
    }

    /**
     * The setter for the guest's title.
     * @param title     The title of the guest
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * The getter for the guest's first name.
     * @return  The first name of the guest.
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * The setter for the guest's first name.
     * @param firstName     The first name of the guest.
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * The getter for the guest's last name.
     * @return  The last name of the guest
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * The setter for the guest's last name
     * @param lastName  The last name of the guest
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * The getter for the guest's address.
     * @return  The address of the guest
     */
    public String getAddress(){
        return address;
    }

    /**
     * The setter for the guest's address
     * @param address   The address of the guest
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * The getter for the guest's email address.
     * @return  The email address of the guest
     */
    public String getEmail(){
        return email;
    }

    /**
     * The setter for the guest's email.
     * @param email     The email of the guest
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * The getter for the guest's telephone number.
     * @return  The telephone number of the guest
     */
    public String getPhone(){
        return phone;
    }

    /**
     * The setter for the guest's telephone number.
     * @param phone     The telephone number of the guest
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * The method used to customise how the object gets handled when included in a String.
     * @return  The full name of the guest
     */
    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}
