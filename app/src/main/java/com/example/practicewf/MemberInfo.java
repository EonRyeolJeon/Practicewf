package com.example.practicewf;

import android.widget.EditText;

public class MemberInfo {

    private String height;
    private String weight;
    private String age;
    private String howmany;
    private String active;
    private String gender;

    public MemberInfo(String height, String weight, String age , String howmany , String active , String gender){
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.howmany = howmany;
        this.active = active;
        this.gender = gender;
    }

    public String getHeight(){
        return this.height;
    }
    public void setHeight(String height){
        this.height = height;
    }
    public String getWeight(){
        return this.weight;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getAge(){
        return this.age;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getHowmany(){
        return this.howmany;
    }
    public void setHowmany(String howmany){
        this.howmany = howmany;
    }
    public String getActive(){return this.active; }
    public void setActive(String active) {this.active = active; }
    public String getGender(){return this.gender; }
    public void setGender(String gender) {this.gender = gender; }

}
