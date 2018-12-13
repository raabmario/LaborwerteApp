package com.example.mario.laborwerteapp;

import android.app.Application;

public class GlobalVariables extends Application{
    int selected_userID;

    public int getSelected_userID() {
        return selected_userID;
    }

    public void setSelected_userID(int selected_userID) {
        this.selected_userID = selected_userID;
    }
}
