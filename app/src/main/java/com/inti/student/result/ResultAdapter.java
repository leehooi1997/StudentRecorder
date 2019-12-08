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

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    Context context;
    ArrayList<Result> results;

    public ResultAdapter(Context c,ArrayList<Result> r)
    {
        context = c;
        results = r;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.result_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mOS.setText("Operating System:"+results.get(position).getOperatingSystem());
        holder.mDI.setText("Data Information: "+results.get(position).getDataInfo());
        holder.mAS.setText("Android Studio:"+results.get(position).getAndroidS());
        holder.mPP.setText("Python Programming:"+results.get(position).getProgramPython());
        holder.mRWP.setText("Real World Project:"+results.get(position).getRealWP());
        holder.mSE.setText("Software Engineering:"+results.get(position).getSoftwareE());
        holder.mSL.setText("Social Legal & Ethics:"+results.get(position).getSocialLegal());
        holder.mCgpa.setText("Cumulative Average:"+results.get(position).getTotalResult()+"%");

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView mOS,mDI,mAS,mPP,mRWP,mSE,mSL,mCgpa;
        public MyViewHolder(View itemView) {
            super(itemView);
            mOS = (TextView) itemView.findViewById(R.id.mOS);
            mSL = (TextView) itemView.findViewById(R.id.mSL);
            mDI = (TextView) itemView.findViewById(R.id.mDI);
            mAS = (TextView) itemView.findViewById(R.id.mAS);
            mPP = (TextView) itemView.findViewById(R.id.mPP);
            mRWP = (TextView) itemView.findViewById(R.id.mRWP);
            mSE = (TextView) itemView.findViewById(R.id.mSE);
            mCgpa = (TextView) itemView.findViewById(R.id.mCgpa);
        }
    }



}
