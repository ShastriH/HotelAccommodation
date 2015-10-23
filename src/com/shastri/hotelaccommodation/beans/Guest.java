package com.shastri.hotelaccommodation.beans;

public class Guest {
    private int guestID = 0;
    private String title = "";
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String email  = "";
    private String phone = "";
    
    public Guest() {}
    
    public Guest(int guestID, String title, String firstName, String lastName, String address, String email, String phone){
        this.guestID = guestID;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getGuestID(){
        return guestID;
    }

    public void setGuestID(int guestID){
        this.guestID = guestID;
    }
    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    
    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}
