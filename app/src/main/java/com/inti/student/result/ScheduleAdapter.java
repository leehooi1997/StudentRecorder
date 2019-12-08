package com.inti.student.result;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    Context context;
    ArrayList<Schedules> schedules;



    public ScheduleAdapter(Context c, ArrayList<Schedules> s){
        context = c;
        schedules = s;

    }
    @NonNull
    @Override
    public ScheduleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.schedule_list,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.MyViewHolder holder, int position) {
        holder.week.setText(schedules.get(position).getSelectDay());
        holder.subject.setText(schedules.get(position).getSelectSub());
        holder.hr.setText(schedules.get(position).getHour()+":");
        holder.mins.setText(schedules.get(position).getMin());

    }
    @Override
    public int getItemCount() {
        return schedules.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView week,mins,hr,subject;
        public MyViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            week = (TextView) itemView.findViewById(R.id.week);
            hr = (TextView) itemView.findViewById(R.id.hr);
            mins = (TextView) itemView.findViewById(R.id.mins);
        }
    }



}
