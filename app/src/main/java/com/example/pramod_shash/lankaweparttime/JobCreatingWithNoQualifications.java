package com.example.pramod_shash.lankaweparttime;

public class JobCreatingWithNoQualifications {
    public String jobName1;
    public String jobDescription1;
    public String numberOfEmployees1;
    public String paymentPerEach1;
    public String date1;
    public String contactNumber1;
    public String location3;
    public String locationAddress3;

    public String getLocationAddress3() {
        return locationAddress3;
    }

    public void setLocationAddress3(String locationAddress3) {
        this.locationAddress3 = locationAddress3;
    }



    public JobCreatingWithNoQualifications(String jobName1, String jobDescription1, String numberOfEmployees1, String paymentPerEach1, String date1, String contactNumber1, String location1,String locationAddress3) {
        this.jobName1 = jobName1;
        this.jobDescription1 = jobDescription1;
        this.numberOfEmployees1 = numberOfEmployees1;
        this.paymentPerEach1 = paymentPerEach1;
        this.date1 = date1;
        this.contactNumber1 = contactNumber1;
        this.location3 = location1;
        this.locationAddress3=locationAddress3;
    }

                        //Helper class for QualificationsNo Activity
}
