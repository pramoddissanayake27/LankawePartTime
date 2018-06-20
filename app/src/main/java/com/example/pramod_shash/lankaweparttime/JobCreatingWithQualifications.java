package com.example.pramod_shash.lankaweparttime;

public class JobCreatingWithQualifications {
    public String jobName;
    public String jobDescription;
    public String numberOfEmployees;
    public String paymentPerEach;
    public String duration;
    public String date;
    public String contactNumber;
    public String location;
    public String locationAddress;

    public JobCreatingWithQualifications(){

    }

    public JobCreatingWithQualifications(String jobName, String jobDescription, String numberOfEmployees, String paymentPerEach, String duration, String date, String contactNumber, String location, String locationAddress) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.numberOfEmployees = numberOfEmployees;
        this.paymentPerEach = paymentPerEach;
        this.duration = duration;
        this.date = date;
        this.contactNumber = contactNumber;
        this.location = location;
        this.locationAddress = locationAddress;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getPaymentPerEach() {
        return paymentPerEach;
    }

    public void setPaymentPerEach(String paymentPerEach) {
        this.paymentPerEach = paymentPerEach;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
   // Helper class for QualificationsYes Activity





