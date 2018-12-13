package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toProfileBtn = (Button) findViewById(R.id.toProfileButton);
        Button toBlutWerteAddBtn = (Button) findViewById(R.id.toBlutwerteAdd);
        Button toBlutwertkategorienAddBtn = (Button) findViewById(R.id.toBlutwertkategorienAdd);
        Button toDisplayBlutwerteBtn = (Button) findViewById(R.id.toDisplayBlutwerte);



    }


    //Intents um zu anderen Activities zu springen

    public void toProfileButtonClicked(View view) {
        openProfileActivity();
    }

    public void toBlutwerteAddClicked(View view) {
        openBlutwerteAdd();
    }

    public void toBlutwertkategorienAddClicked(View view) {
        openBlutwertkategorieAdd();
    }

    public void toDisplayBlutwerteClicked(View view) {openDisplayBlut();
    }

    public void dBTestClicked(View view) {
        opendbTestActivity();
    }

    //Erstellen der Intents

    public void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openBlutwertkategorieAdd(){
        Intent intent = new Intent(this, CreateBlutwertkategorieActivity.class);
        startActivity(intent);
    }

    public void opendbTestActivity(){
        Intent intent = new Intent(this, DBSelectionActivity.class);
        startActivity(intent);
    }

    public void openBlutwerteAdd(){
        Intent intent = new Intent (this, CreateEintragActivity.class);
        startActivity(intent);
    }

    public void openDisplayBlut(){
        Intent intent = new Intent(this, AnalysisDataSelection.class);
        startActivity(intent);
    }


}
