package com.prathmeshranaut.attendancemsit.general;

/**
 * Created by Jatin on 8/4/2017.
 */

public class firebase_class_attendance {
    String date;
    String time;
    int no_of_present;
    int no_of_absent;
    int student_id;
    int teacher_id;
    firebase_class_attendance()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNo_of_present() {
        return no_of_present;
    }

    public void setNo_of_present(int no_of_present) {
        this.no_of_present = no_of_present;
    }

    public int getNo_of_absent() {
        return no_of_absent;
    }

    public void setNo_of_absent(int no_of_absent) {
        this.no_of_absent = no_of_absent;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public firebase_class_attendance(String date, String time, int no_of_present, int no_of_absent, int student_id, int teacher_id)
    {
        this.date=date;
        this.time=time;
        this.no_of_present=no_of_present;
        this.no_of_absent=no_of_absent;

        this.student_id=student_id;
        this.teacher_id=teacher_id;
    }
}
