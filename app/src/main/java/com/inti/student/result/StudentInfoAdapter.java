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

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.MyViewHolder> {

    Context context;
    ArrayList<Students> students;

    public StudentInfoAdapter(Context c,ArrayList<Students> s)
    {
        context = c;
        students = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.subject.setText(students.get(position).getStudentSub());
        holder.coursework.setText("Coursework: "+students.get(position).getStudentCourse());
        holder.exam.setText("Exam :"+students.get(position).getStudentExam());
        holder.score.setText("Final Score:"+students.get(position).getStudentScore());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView coursework,exam,score,subject;
        public MyViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            coursework = (TextView) itemView.findViewById(R.id.coursework);
            exam = (TextView) itemView.findViewById(R.id.exam);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    }



}
