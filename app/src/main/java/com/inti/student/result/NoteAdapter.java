package com.inti.student.result;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    ArrayList<Announcement> announcements;

    public NoteAdapter(Context c,ArrayList<Announcement> s)
    {
        context = c;
        announcements = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.note_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTitle.setText(announcements.get(position).getStudentTitle());
        holder.mNote.setText(announcements.get(position).getStudentNote());


    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle,mNote;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.mTitle);
            mNote = (TextView) itemView.findViewById(R.id.mNote);

        }
    }



}
