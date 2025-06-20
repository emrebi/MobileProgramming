package com.example.holidayapp.model;

public class HolidayModel {
    private String name;
    private String date;

    public HolidayModel(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
