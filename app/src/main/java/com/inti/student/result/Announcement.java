package com.inti.student.result;

public class Announcement  {
    private String studentTitle;
    private String studentNote;


    public Announcement(){


    }

    public Announcement(String studentTitle,  String studentNote) {
        this.studentTitle = studentTitle;
        this.studentNote = studentNote;

    }

    public String getStudentTitle() {
        return studentTitle;
    }

    public void setStudentTitle(String studentTitle){
        this.studentTitle=studentTitle;
    }
    public String getStudentNote(){
        return studentNote;
    }
    public void setStudentNote(String studentNote){
        this.studentNote = studentNote;
    }




}
