package com.example.pramod_shash.lankaweparttime;

public class EmployerUserProfile {
    public String companyName, email1;

    public  EmployerUserProfile(){


    }


    public EmployerUserProfile(String companyName, String email1) {
        this.companyName = companyName;
        this.email1 = email1;


    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }
}
