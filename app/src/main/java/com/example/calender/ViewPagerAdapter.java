package com.example.calender;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter{
    List<String> dateList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> dateList) {
        super(fragmentActivity);
        this.dateList = dateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return WeekViewFragment.getInstance(dateList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
