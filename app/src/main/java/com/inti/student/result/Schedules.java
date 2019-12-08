package com.inti.student.result;

import android.content.Intent;

public class Schedules {
    private String studentId;
    private String selectDay;
    private String selectSub;
    private String hour;
    private String min;

    public Schedules(){


    }

    public Schedules(String studentId,String selectDay,  String selectSub,String hour, String min) {
        this.studentId = studentId;
        this.selectDay = selectDay;
        this.selectSub = selectSub;
        this.hour = hour;
        this.min =min;

    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public String getSelectDay() {
        return selectDay;
    }
    public void setSelectDay(String selectDay){
        this.selectDay = selectDay;
    }
    public String getSelectSub(){
        return selectSub;
    }
    public void setSelectSub(String selectSub){
        this.selectSub = selectSub;
    }

    public String getHour() {
        return hour;
    }
    public void setHour(String hour){
        this.hour= hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
