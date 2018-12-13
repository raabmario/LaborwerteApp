package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditProfileActivity extends Activity {
    MyDBHelper dbHelper;
    EditText Vorname;
    EditText Name;
    EditText Email;
    EditText DOB;
    Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        dbHelper = new MyDBHelper(this, null, null, 1);

        //Spinner layout
        Spinner dropdown = findViewById(R.id.spinnerGeschlecht);
        String[] items = new String[]{"Mann", "Frau"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        int db_SpinnerItem =0;

        //Zuweisung der EditText Felder
        Vorname = (EditText)findViewById(R.id.inputVorname);
        Name = (EditText) findViewById(R.id.inputName);
        Email = (EditText)findViewById(R.id.inputEmail);
        DOB = (EditText)findViewById(R.id.inputDOB);

        //Zuweisung Buttons
        changeBtn = (Button) findViewById(R.id.changeProfile);


        //Datenbank durchsuchen
        GlobalVariables globalvariables = (GlobalVariables) getApplicationContext();
        int selected_user = globalvariables.getSelected_userID();
        if(selected_user == 0){
            Toast.makeText(EditProfileActivity.this, "Bitte Profil auswählen!", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor c = dbHelper.getUID_relatedData(selected_user);
        if(c.getCount() == 0) {
            Toast.makeText(EditProfileActivity.this, "cursor has no data", Toast.LENGTH_SHORT).show();
            return;
        }

        while (c.moveToNext()){
            //Felder befüllen
            Vorname.setText(c.getString(1));
            Name.setText(c.getString(2));
            Email.setText(c.getString(3));
            DOB.setText(c.getString(4));
            if(c.getString(5).equals("Mann")){db_SpinnerItem = 0;}
            if(c.getString(5).equals("Frau")){db_SpinnerItem = 1;}
            dropdown.setSelection(db_SpinnerItem);
        }

    }
    public void changeProfileClicked(View view) {
        dbHelper = new MyDBHelper(this, null, null, 1);
        Spinner dropdown = findViewById(R.id.spinnerGeschlecht);
        GlobalVariables globalvariables = (GlobalVariables) getApplicationContext();
        int selected_user = globalvariables.getSelected_userID();
        dbHelper.editUser(selected_user, Vorname.getText().toString(), Name.getText().toString(), Email.getText().toString(), DOB.getText().toString(),dropdown.getSelectedItem().toString());

        Toast.makeText(EditProfileActivity.this, "Ihr Profil wurde geändert!",Toast.LENGTH_SHORT).show();
    }



}
