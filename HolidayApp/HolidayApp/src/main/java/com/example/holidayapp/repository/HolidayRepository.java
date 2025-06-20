package com.example.holidayapp.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.holidayapp.model.HolidayModel;
import java.util.ArrayList;
import java.util.List;

public class HolidayRepository {
    private MutableLiveData<List<HolidayModel>> mutableLiveData = new MutableLiveData<>();

    public HolidayRepository() {
        // Simulate API data
        List<HolidayModel> holidayList = new ArrayList<>();
        holidayList.add(new HolidayModel("New Year's Day", "2023-01-01"));
        holidayList.add(new HolidayModel("Labor Day", "2023-05-01"));
        holidayList.add(new HolidayModel("Republic Day", "2023-10-29"));
        mutableLiveData.setValue(holidayList);
    }

    public MutableLiveData<List<HolidayModel>> getHolidays() {
        return mutableLiveData;
    }
}
