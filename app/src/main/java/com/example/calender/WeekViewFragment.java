package com.example.calender;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekViewFragment extends Fragment {
    public static RecyclerView rvDate;
    private int NUM_DAYS = 7; // You can get as many dates as you want.
    private Calendar calendar = Calendar.getInstance();
    String date;

    public static WeekViewFragment getInstance(String date) {
        WeekViewFragment fragment = new WeekViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", date);
        fragment.setArguments(bundle);
        return fragment;
    }

    public WeekViewFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = (String) getArguments().get("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_view, container, false);

        calendar.setFirstDayOfWeek(Calendar.MONDAY); // The first day you want dates from.
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        rvDate = view.findViewById(R.id.rv_date_fragment);

        showWeek();

        return view;
    }

    private void showWeek() {
        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < NUM_DAYS; i++) {
            Date date = calendar.getTime();

            dateList.add(new SimpleDateFormat("dd").format(date));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        CellDayAdapter adapter = new CellDayAdapter(getActivity(), dateList);
        rvDate.setAdapter(adapter);
    }
}