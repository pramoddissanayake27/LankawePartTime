package com.example.pramod_shash.lankaweparttime;

public class EmployeeUserProfile { //this class is used to send the data of the employee to the database in the registration
    public String username;
    public String email;
    public String mobileNumber;

    public  EmployeeUserProfile(){}

    public EmployeeUserProfile(String username, String email, String mobileNumber) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
