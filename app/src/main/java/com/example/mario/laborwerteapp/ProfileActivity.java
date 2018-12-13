package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button toEditProfileClickedBtn = (Button) findViewById(R.id.toEditProfile);
        Button toCreateProfileClickedBtn = (Button) findViewById(R.id.toCreateProfile);
        Button toSelectProfileClickedBtn = (Button) findViewById(R.id.toSelectProfile);


    }

    //Intents um zu weiteren Activities zu kommen

    public void toEditProfileClicked(View view) {
        openEditProfileActivity();
    }

    public void toCreateProfileClicked(View view) {
        openCreateProfileActivity();
    }

    public void toSelectProfileClicked(View view) {
        openSelectProfileActvity();
    }

    //Methoden die Intent generieren

    public void openEditProfileActivity(){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void openCreateProfileActivity(){
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }

    public void openSelectProfileActvity(){
        Intent intent = new Intent(this, UserSelectionListActivity.class);
        startActivity(intent);
    }
}
