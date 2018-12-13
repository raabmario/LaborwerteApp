package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateBlutwertkategorieActivity extends Activity {

    EditText Abkuerzung;
    EditText NameB;
    EditText Beschreibung;
    EditText Einheit;
    EditText NormalRangeLow;
    EditText NormalRangeUpp;
    MyDBHelper dbHelper;
    Spinner GeschlechtB;
    Spinner Altersgruppe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blutwertkategorie);
        dbHelper = new MyDBHelper(this, null, null, 1);

        GeschlechtB = findViewById(R.id.spinnerGender);
        String[] items1 = new String[]{"Mann", "Frau"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        //WENN ES FUNKTIONIERT DIE AUSKOMMENTIERTEN ZEILEN AUS CODE ENTFERNEN!!
        //ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.genderSpinner,android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GeschlechtB.setAdapter(adapter1);

        Altersgruppe = findViewById(R.id.spinnerAgeGroup);
        String[] items2 = new String[]{"Erwachsen", "Kind"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        //ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.ageGroupSpinner,android.R.layout.simple_spinner_item);
        //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Altersgruppe.setAdapter(adapter2);

        Abkuerzung = (EditText)findViewById(R.id.editShortcut);
        NameB = (EditText)findViewById(R.id.editName);
        Beschreibung = (EditText)findViewById(R.id.editDescription);
        Einheit = (EditText)findViewById(R.id.editUnit);
        NormalRangeLow = (EditText)findViewById(R.id.editNormRangeLow);
        NormalRangeUpp = (EditText)findViewById(R.id.editNormRangeUpp);
    }

    public void buttonAddLabValueClicked (View view){
        //LabValues lab_Values = new LabValues(Abkuerzung.getText().toString(), NameB.getText().toString(), Beschreibung.getText().toString(), Altersgruppe.getSelectedItem().toString(), GeschlechtB.getSelectedItem().toString(), NormalRangeLow.getText().toString(), NormalRangeUpp.getText().toString(), Einheit.getText().toString());
        double norm_low = Double.parseDouble(NormalRangeLow.getText().toString());
        double norm_high = Double.parseDouble(NormalRangeUpp.getText().toString());
        Blutwertkategorie blutwertkategorie = new Blutwertkategorie(Abkuerzung.getText().toString(), NameB.getText().toString(), Beschreibung.getText().toString(),Altersgruppe.getSelectedItem().toString(), GeschlechtB.getSelectedItem().toString(), norm_low, norm_high, Einheit.getText().toString() );
        //LabValues lab_Values = new LabValues(Abkuerzung.getText().toString(), NameB.getText().toString(), Beschreibung.getText().toString(), Altersgruppe.getSelectedItem().toString(), GeschlechtB.getSelectedItem().toString(), 12, 12, Einheit.getText().toString());
        //dbHelper.addValues(lab_Values);
        dbHelper.addBlutwertkategorie(blutwertkategorie);
        Toast.makeText(CreateBlutwertkategorieActivity.this, "Ihre Blutwertkategorie wurde erstellt!",Toast.LENGTH_SHORT).show();

    }

}
