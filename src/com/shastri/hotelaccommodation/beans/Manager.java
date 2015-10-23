package com.shastri.hotelaccommodation.beans;

public class Manager {
    private int managerID = 0;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    
    public Manager(int managerID, String firstName, String lastName, String email, String phone){
        this.managerID = managerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getManagerID(){
        return managerID;
    }

    public void setManagerID(int managerID){
        this.managerID = managerID;
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
