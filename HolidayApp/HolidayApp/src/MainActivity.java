package com.example.holidayapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HolidayViewModel holidayViewModel;
    private ListView holidayListView;
    private ArrayAdapter<String> adapter;
    private List<String> holidayNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holidayListView = findViewById(R.id.holidayListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, holidayNames);
        holidayListView.setAdapter(adapter);

        holidayViewModel = new ViewModelProvider(this).get(HolidayViewModel.class);
        holidayViewModel.getHolidays().observe(this, new Observer<List<HolidayModel>>() {
            @Override
            public void onChanged(List<HolidayModel> holidayModels) {
                holidayNames.clear();
                for (HolidayModel holiday : holidayModels) {
                    holidayNames.add(holiday.getName() + " - " + holiday.getDate());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
