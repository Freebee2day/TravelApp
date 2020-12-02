package com.example.travelapp.Classes;

import java.util.Date;

public class Task {

    String taskName;
    String string_date;
    int id;

    public Task(String taskName, String string_date, int id) {
        this.taskName = taskName;
        this.string_date = string_date;
        this.id = id;
    }

    public Task() {
    }

    //setter and getter
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStringDate() {
        return string_date;
    }

    public void setDate(String string_date) {
        this.string_date = string_date;
    }

    public int getTaskId() {
        return id;
    }

    //printing out member variables.
/*    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }*/
}
