package com.example.holidayapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.holidayapp.model.HolidayModel;
import com.example.holidayapp.repository.HolidayRepository;
import java.util.List;

public class HolidayViewModel extends ViewModel {
    private HolidayRepository holidayRepository;
    private LiveData<List<HolidayModel>> holidays;

    public HolidayViewModel() {
        holidayRepository = new HolidayRepository();
        holidays = holidayRepository.getHolidays();
    }

    public LiveData<List<HolidayModel>> getHolidays() {
        return holidays;
    }
}
