package com.cadi.lookingfordroidventure.models;

/**
 * Created by cadi on 13/06/15.
 */
public class Departure {

    private Long id;
    private String time;
    private String calendar;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCalendar() {

        return calendar;
    }

    public void setCalendar(String calendar) {

        this.calendar = calendar;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }
}
