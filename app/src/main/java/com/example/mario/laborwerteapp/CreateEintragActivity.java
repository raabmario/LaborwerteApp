package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreateEintragActivity extends Activity {


    EditText datumText;
    EditText blutwertText;
    TextView unitTextView;
    MyDBHelper dbHelper;
    Spinner dropdown;

    //Für mehrere Funktionen gebrauchte Variablen

    String blutID ="";
    String unit ="";
    int selected_user;

    //Hilfsvariablen
    String ueunit;
    String ueBlutID;
    String uenormlow;
    String uenormhigh;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_eintrag);
        dbHelper = new MyDBHelper(this, null, null, 1);

        GlobalVariables globalvariables = (GlobalVariables) getApplicationContext();
        selected_user = globalvariables.getSelected_userID();

        //Zuweisung der EditTexts
        datumText = (EditText)findViewById(R.id.eintragDatum);
        blutwertText = (EditText)findViewById(R.id.eintragBlutwert);

        //Zuweisung TextView
        unitTextView = (TextView) findViewById(R.id.unitTextView);

        //Zuweisung und Befüllung des Spinners
        dropdown = (Spinner)findViewById(R.id.spinnerBlutwertkategorie);

        //User ID auslesen und werte in cursor schreiben

        if(selected_user == 0){
            Toast.makeText(CreateEintragActivity.this, "Bitte Profil auswählen!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Spinner nur mit den Blutkategorien befüllen, die auch für den angemeldeten User auswählbar sind!!
        Cursor d = dbHelper.findApplicableBlutwertkategoriebyUID(selected_user);
        if(d.getCount() == 0) {
            Toast.makeText(CreateEintragActivity.this, "blutwertkat cursor has no data", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        StringBuffer unit_buffer = new StringBuffer();
        StringBuffer blutkategorieID_buffer = new StringBuffer();
        StringBuffer normlow_buffer = new StringBuffer();
        StringBuffer normhigh_buffer = new StringBuffer();


        while (d.moveToNext()){

            blutkategorieID_buffer.append(d.getString(0)+"\n");
            buffer.append(d.getString(1)+"\n");
            normlow_buffer.append(d.getString(6)+"\n");
            normhigh_buffer.append(d.getString(7)+"\n");
            unit_buffer.append(d.getString(8)+"\n");
        }
        String data = buffer.toString();
        ueunit = unit_buffer.toString();
        ueBlutID = blutkategorieID_buffer.toString();
        uenormlow = normlow_buffer.toString();
        uenormhigh = normhigh_buffer.toString();
        final String [] spinnerfiller = data.split("\n");


        //Spinner füllen
        String[] items = spinnerfiller;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        //Wenn ein item ausgewählt ist, dann bitte entsprechende Unit in die TextView der Einheit eintragen

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView mit Einheit der ausgewählten Blutwertkategorie füllen
                final String [] unit_buffer_array = ueunit.split("\n");
                unitTextView.setText(unit_buffer_array[dropdown.getSelectedItemPosition()]);

                //Toast anzeigen mit Normwerten der ausgewählten Blutwertkategorie
                final String [] normlow_buffer_array = uenormlow.split("\n");
                final String [] normhigh_buffer_array = uenormhigh.split("\n");

                String norm2display = "Ihre ausgewählte Blutwertkategorie besitzt folgende Normwerte:\n" +
                        "Normbereichsgrenze unten: "+normlow_buffer_array[dropdown.getSelectedItemPosition()]+"\n" +
                        "Normbereichsgrenze oben: "+normhigh_buffer_array[dropdown.getSelectedItemPosition()];

                Toast.makeText(CreateEintragActivity.this, norm2display, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void createBlutwertClicked(View view) {

        final String [] blutID_buffer_array = ueBlutID.split("\n");
        final String [] unit_buffer_array = ueunit.split("\n");


        blutID = blutID_buffer_array[dropdown.getSelectedItemPosition()];
        unit = unit_buffer_array[dropdown.getSelectedItemPosition()];

        Eintrag eintrag = new Eintrag(String.valueOf(selected_user), blutID, blutwertText.getText().toString(),datumText.getText().toString());
        dbHelper.addEintrag(eintrag);
        Toast.makeText(CreateEintragActivity.this, "Eintrag angelegt!", Toast.LENGTH_SHORT).show();
    }

}
