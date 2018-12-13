package com.example.mario.laborwerteapp;

public class Blutwertkategorie {
    private  int _id;
    private String abk;
    private String name;
    private String description;
    private String ageGroup;
    private String gender;
    private double normRangeLow;
    private double normRangeHigh;
    private String unit;

    //Konstruktor
    public Blutwertkategorie(String abk, String name, String description, String ageGroup, String gender, double normRangeLow, double normRangeHigh, String unit) {
        this.abk = abk;
        this.name = name;
        this.description = description;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.normRangeLow = normRangeLow;
        this.normRangeHigh = normRangeHigh;
        this.unit = unit;
    }

    //Getter Methoden


    public int get_id() {
        return _id;
    }

    public String getAbk() {
        return abk;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public double getNormRangeLow() {
        return normRangeLow;
    }

    public double getNormRangeHigh() {
        return normRangeHigh;
    }

    public String getUnit() {
        return unit;
    }

    //Setter Methoden

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setAbk(String abk) {
        this.abk = abk;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNormRangeLow(double normRangeLow) {
        this.normRangeLow = normRangeLow;
    }

    public void setNormRangeHigh(double normRangeHigh) {
        this.normRangeHigh = normRangeHigh;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
