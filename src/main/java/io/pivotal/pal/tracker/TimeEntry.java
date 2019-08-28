package io.pivotal.pal.tracker;

import com.fasterxml.jackson.annotation.JsonProperty;

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
                ", parse=" + parse +
                ", i=" + i +
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

    public LocalDate getParse() {
        return parse;
    }

    public void setParse(LocalDate parse) {
        this.parse = parse;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
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
    @JsonProperty("date")
    LocalDate parse;
    @JsonProperty("hours")
    int i;
    public TimeEntry(long projectId, long userId, LocalDate parse, int i) {
this.projectId=projectId;
this.userId=userId;
this.parse=parse;
this.i=i;
    }
    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate parse, int i) {
        this.id=timeEntryId;
        this.projectId=projectId;
        this.userId=userId;
        this.parse=parse;
        this.i=i;
    }

}
