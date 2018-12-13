package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AnalysisDataSelection extends Activity {

    Button GrafikAnzeigeButton;
    Button ListeAnzeigeButton;

    EditText Datum_von;
    EditText Datum_bis;

    TextView Beschreibung;

    Spinner dropdown;

    MyDBHelper dbHelper;

    int selected_user;
    String beschreibungen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_data_selection);

        //Zuweisung von globalen Daten und dbHelper
        dbHelper = new MyDBHelper(this, null, null, 1);

        GlobalVariables globalvariables = (GlobalVariables) getApplicationContext();
        selected_user = globalvariables.getSelected_userID();

        //Zuweisung der UI
        GrafikAnzeigeButton = (Button) findViewById(R.id.GrafikAnzeigeBtn);
        ListeAnzeigeButton = (Button) findViewById(R.id.ListeAnzeigeBtn);

        Datum_von = (EditText) findViewById(R.id.inputEintraegeAb);
        Datum_bis = (EditText) findViewById(R.id.inputEintraegeBis);

        Beschreibung = (TextView) findViewById(R.id.BeschreibungBWK);

        dropdown = (Spinner) findViewById(R.id.spinnerBlutwertkategorie4Analysis);

        //Spinner nur mit den Blutkategorien befüllen, die auch für den angemeldeten User auswählbar sind!!
        Cursor d = dbHelper.findApplicableBlutwertkategoriebyUID(selected_user);
        if(d.getCount() == 0) {
            Toast.makeText(AnalysisDataSelection.this, "blutwertkat cursor has no data", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        StringBuffer blutkategorieID_buffer = new StringBuffer();
        StringBuffer beschreibung_buffer = new StringBuffer();



        while (d.moveToNext()){

            blutkategorieID_buffer.append(d.getString(0)+"\n");
            buffer.append(d.getString(1)+"\n");
            beschreibung_buffer.append(d.getString(2)+"\n");

        }
        String data = buffer.toString();
        beschreibungen = beschreibung_buffer.toString();

        final String [] spinnerfiller = data.split("\n");
        final String [] beschreibungs_array = beschreibungen.split("\n");


        //Spinner füllen
        String[] items = spinnerfiller;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView mit Beschreibung der ausgewählten Blutwertkategorie füllen

                Beschreibung.setText("Beschreibung der ausgewählten Blutwertkategorie:\n"+beschreibungs_array[dropdown.getSelectedItemPosition()]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void ListeAnzeigeBtnClicked(View view) {

    }

    public void GrafikAnzeigeBtnClicked(View view) {
    }

    public void toListeAnzeige(){
        //intent um auf andere Anzeige zu kommen
    }

    public void toGrafikAnzeige(){
        //intent um auf andere Anzeige zu kommen
    }
}
