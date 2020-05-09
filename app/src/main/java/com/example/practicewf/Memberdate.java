package com.example.practicewf;

import android.app.Application;

public class Memberdate  {

    private String year;
    private String month;
    private String dayofmonth;

    public String getYear(){
        return this.year;
    }
    public void setYear(String year){
        this.year = year;
    }
    public String getMonth(){
        return this.month;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public String getDayofmonth(){
        return this.dayofmonth;
    }
    public void setDayofmonth(String dayofmonth){
        this.dayofmonth = dayofmonth;
    }

    private static Memberdate instance = null;

    public static synchronized Memberdate getInstance(){
        if(null == instance){
            instance = new Memberdate();
        }
        return instance;
    }

}
