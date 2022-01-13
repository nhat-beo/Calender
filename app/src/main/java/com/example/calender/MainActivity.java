package com.example.calender;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
//    private RecyclerView rvDate;

    private TextView tvMonth;

    public static AppCompatButton prev, next;

    private ViewPager2 vpDate;
    ViewPagerAdapter viewPagerAdapter;

    int NUM_DAYS = 7; // You can get as many dates as you want.
    Calendar calendar;
    List<String> dateList;

    private int currentPosition = 0;
    private int scrollState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // The first day you want dates from.
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        tvMonth = findViewById(R.id.tv_month);
        prev = findViewById(R.id.btn_prev);
        next = findViewById(R.id.btn_next);

//        rvDate = findViewById(R.id.rv_date);

        vpDate = findViewById(R.id.vp_date);

//        showWeek();

        vpDate.registerOnPageChangeCallback(new OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                handleScrollState(state);
                scrollState = state;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, getDateList());
        vpDate.setAdapter(viewPagerAdapter);
        vpDate.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
//        vpDate.setCurrentItem(1, false);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DATE, -14);
                nextWeek();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextWeek();
            }
        });
    }

    //Get 7 days
    private List<String> getDateList() {
        dateList = new ArrayList<>();
        for (int i = 0; i < NUM_DAYS; i++) {
            Date date = calendar.getTime();
            tvMonth.setText("Tháng " + new SimpleDateFormat("MM, yyyy").format(date));

            dateList.add(new SimpleDateFormat("dd").format(date));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateList;
    }

    //View pager loop
    private void handleScrollState(int state) {
        if (state == ViewPager2.SCROLL_STATE_IDLE) {
            if (!isScrollStateSettling()) {
                int lastPosition = Objects.requireNonNull(vpDate.getAdapter()).getItemCount() - 1;
                if (currentPosition == 0) {
                    vpDate.setCurrentItem(lastPosition, false);
                } else if (currentPosition == lastPosition) {
                    vpDate.setCurrentItem(0, false);
                }
            }
        }
    }

    private boolean isScrollStateSettling() {
        return scrollState == ViewPager2.SCROLL_STATE_SETTLING;
    }

    private void nextWeek() {
        getDateList();
        CellDayAdapter adapter = new CellDayAdapter(MainActivity.this, dateList);
        WeekViewFragment.rvDate.setAdapter(adapter);
    }

//    private void showWeek() {
//        List<String> dateList = new ArrayList<>();
//        for (int i = 0; i < NUM_DAYS; i++) {
//            Date date = calendar.getTime();
//            tvMonth.setText("Tháng " + new SimpleDateFormat("MM, yyyy").format(date));
//
//            dateList.add(new SimpleDateFormat("dd").format(date));
//            Log.d("WEEK", new SimpleDateFormat("EEEE dd/MM/yyyy").format(date));
//
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//            daysAdapter = new CellDayAdapter(MainActivity.this, dateList);
//            rvDate.setAdapter(daysAdapter);
//        }
//    }
}