package com.example.pramod_shash.lankaweparttime;

public class EmployeeUserProfile { //this class is used to send the data of the employee to the database in the registration
    public String username;
    public String email;
    public String mobileNumber;

    public EmployeeUserProfile(String username, String email, String mobileNumber) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
