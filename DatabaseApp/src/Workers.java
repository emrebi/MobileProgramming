package com.example.employeedbapp;

public class Calisanlar {
    private int id;
    private String lName;
    private String fName;
    private String email;
    private byte[] image;

    public Calisanlar(int id, String lName, String fName, String email, byte[] image) {
        this.id = id;
        this.lName = lName;
        this.fName = fName;
        this.email = email;
        this.image = image;
    }

    public Calisanlar() {}

    public Calisanlar(String lName, String fName, String email, byte[] image) {
        this.lName = lName;
        this.fName = fName;
        this.email = email;
        this.image = image;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getlName() { return lName; }
    public void setlName(String lName) { this.lName = lName; }
    public String getfName() { return fName; }
    public void setfName(String fName) { this.fName = fName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    @Override
    public String toString() {
        return getfName() + " " + getlName() + " " + getEmail();
    }
}
