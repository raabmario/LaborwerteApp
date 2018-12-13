package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateProfileActivity extends Activity {

    EditText Vorname;
    EditText Name;
    EditText Email;
    EditText DOB;
    MyDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        dbHelper = new MyDBHelper(this, null, null, 1);

        //Spinner layout
        Spinner dropdown = findViewById(R.id.spinnerGeschlecht);
        String[] items = new String[]{"Mann", "Frau"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Text Connection

        Vorname = (EditText)findViewById(R.id.inputVorname);
        Name = (EditText) findViewById(R.id.inputName);
        Email = (EditText)findViewById(R.id.inputEmail);
        DOB = (EditText)findViewById(R.id.inputDOB);




    }

    public void createButtonClicked(View view) {

        Spinner mySpinner = findViewById(R.id.spinnerGeschlecht);
        Users user = new Users(Vorname.getText().toString(), Name.getText().toString(), Email.getText().toString(), DOB.getText().toString(), mySpinner.getSelectedItem().toString());
        dbHelper.addUser(user);
        Toast.makeText(CreateProfileActivity.this, "Ihr Profil wurde erstellt!",Toast.LENGTH_SHORT).show();

    }

}