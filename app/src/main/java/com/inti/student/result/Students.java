package com.inti.student.result;

public class Students  {
    private String studentId;
    private String studentCourse;
    private String studentExam;
    private String studentSub;
    private String studentScore;

    public Students(){


    }

    public Students(String studentId,  String studentSub,String studentCourse,String studentExam, String studentScore) {
        this.studentId = studentId;
        this.studentSub = studentSub;
        this.studentCourse = studentCourse;
        this.studentExam = studentExam;
        this.studentScore= studentScore;

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId){
        this.studentId=studentId;
    }
    public String getStudentSub(){
        return studentSub;
    }
    public void setStudentSub(String studentSub){
        this.studentSub = studentSub;
    }

    public String getStudentCourse() {
        return studentCourse;
    }
    public void setStudentCourse(String studentCourse){
        this.studentCourse = studentCourse;
    }

    public String getStudentExam(){
        return studentExam;
    }
    public void setStudentExam(String studentExam){
        this.studentExam=studentExam;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore){
        this.studentScore=studentScore;
    }


}
