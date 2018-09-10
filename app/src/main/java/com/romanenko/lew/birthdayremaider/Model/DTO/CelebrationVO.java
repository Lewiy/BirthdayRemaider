package com.romanenko.lew.birthdayremaider.Model.DTO;


public class CelebrationVO {

    private String firstName;
    private String lastName;
    private String date;
    private String fotoPath;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public String getFotoPath() {
        return fotoPath;
    }


}
