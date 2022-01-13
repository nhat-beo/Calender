package com.example.calender;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CellDayAdapter extends RecyclerView.Adapter<CellDayViewHolder> {
    private final List<String> dateList;
    private Context context;
    private View view;

    public CellDayAdapter(Context context, List<String> dateList) {
        this.context = context;
        this.dateList = dateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CellDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.cell_day, parent, false);
        return new CellDayViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CellDayViewHolder holder, int position) {
        String value = dateList.get(position);
        if (value == null) return;

        holder.tvDate.setText(value);
        if (position == 6) {
            holder.tvDate.setText("CN\n" + value);
        } else {
            int startDate = position + 1;
            holder.tvDate.setText(("T" + (startDate + 1) + "\n") + value);
        }
    }

    @Override
    public int getItemCount() {
        if (dateList != null) {
            return dateList.size();
        }
        return 0;
    }
}

class CellDayViewHolder extends RecyclerView.ViewHolder {
    public TextView tvDate;

    public CellDayViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tv_date);
    }
}
