package com.example.aroundtheworldin80meals.data.meal.model;


import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("strArea")
    private String name;
    private int flag;

    public Area(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}