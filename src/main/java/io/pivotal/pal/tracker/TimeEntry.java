package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

    public TimeEntry() {

    }

    @Override
    public boolean equals(Object obj) {
        TimeEntry entry = (TimeEntry)obj;

       if(entry.id==this.id){
           return true;
       }
       return false;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", parse=" + date +
                ", i=" + hours +
                '}';
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    long id;
    long projectId;
    long userId;

   // @JsonProperty("date")
    LocalDate date;
   // @JsonProperty("hours")
    int hours;
    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
this.projectId=projectId;
this.userId=userId;
this.date = date;
this.hours = hours;
    }
    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate date, int hours) {
        this.id=timeEntryId;
        this.projectId=projectId;
        this.userId=userId;
        this.date = date;
        this.hours = hours;
    }

}
