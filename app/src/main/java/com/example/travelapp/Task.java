package com.example.travelapp;

import java.util.Date;

public class Task {

    String taskName;
    long date;
    int id;

    public Task(String taskName, long date, int id) {
        this.taskName = taskName;
        this.date = date;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTaskId() {
        return id;
    }

    //printing out member variables.
    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
