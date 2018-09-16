package com.romanenko.lew.birthdayremaider.Model.DTO;


public class CelebrationVO {

    private String firstName;
    private String lastName;
    private String date;
    private String fotoPath;
    private long idUser;
    private String typeCelebration;


    public String getTypeCelebration() {
        return typeCelebration;
    }

    public void setTypeCelebration(String typeCelebration) {
        this.typeCelebration = typeCelebration;
    }
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    private String comment;

    public long getIdUser() {
        return idUser;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


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
