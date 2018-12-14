package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Boolean firstTime = null;
    MyDBHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toProfileBtn = (Button) findViewById(R.id.toProfileButton);
        Button toBlutWerteAddBtn = (Button) findViewById(R.id.toBlutwerteAdd);
        Button toBlutwertkategorienAddBtn = (Button) findViewById(R.id.toBlutwertkategorienAdd);
        Button toDisplayBlutwerteBtn = (Button) findViewById(R.id.toDisplayBlutwerte);
        dbHelper = new MyDBHelper(this, null, null, 1);

        boolean first = isFirstTime(); //METHODE FINDET HERAUS OB DIE APP ZUM ERSTEN MAL GEÖFFNET WURDE ODER NICHT!


        if(first == true){
            prepopulateDB();
        }




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

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        } else {
            firstTime = false;
        }
        return firstTime;
    }

    public void prepopulateDB(){
        Blutwertkategorie [] bwks = new Blutwertkategorie[32];
        bwks[0] = new Blutwertkategorie("Ery", "Erythrozyten","Anzahl der roten Blutkörperchen (Erythrozyten)","Kind","Mann",3.89,5.03,"Mio./mikro l");
        bwks[1] = new Blutwertkategorie("Ery", "Erythrozyten","Anzahl der roten Blutkörperchen (Erythrozyten)","Kind","Frau",3.84,4.96,"Mio./mikro l");
        bwks[2] = new Blutwertkategorie("Ery", "Erythrozyten","Anzahl der roten Blutkörperchen (Erythrozyten)","Erwachsen","Mann",4.3,5.7,"Mio./mikro l");
        bwks[3] = new Blutwertkategorie("Ery", "Erythrozyten","Anzahl der roten Blutkörperchen (Erythrozyten)","Erwachsen","Frau",3.9,5.3,"Mio./mikro l");
        bwks[4] = new Blutwertkategorie("Leuko", "Leukozyten","Anzahl der weißen Blutkörperchen (Leukozyten)", "Kind", "Mann", 5140,11000,"pro mikro l");
        bwks[5] = new Blutwertkategorie("Leuko", "Leukozyten","Anzahl der weißen Blutkörperchen (Leukozyten)", "Kind", "Frau", 4860,11400,"pro mikro l");
        bwks[6] = new Blutwertkategorie("Leuko", "Leukozyten","Anzahl der weißen Blutkörperchen (Leukozyten)", "Erwachsen", "Mann", 3800,10500,"pro mikro l");
        bwks[7] = new Blutwertkategorie("Leuko", "Leukozyten","Anzahl der weißen Blutkörperchen (Leukozyten)", "Erwachsen", "Frau", 3800,10500,"pro mikro l");
        bwks[8] = new Blutwertkategorie("Thrombo", "Thrombozyten", "Anzahl der Blutplättchen (Thrombozyten","Kind", "Mann",  202000,369000, "pro mikro l");
        bwks[9] = new Blutwertkategorie("Thrombo", "Thrombozyten", "Anzahl der Blutplättchen (Thrombozyten","Kind", "Frau",  189000,367000, "pro mikro l");
        bwks[10] = new Blutwertkategorie("Thrombo", "Thrombozyten", "Anzahl der Blutplättchen (Thrombozyten","Erwachsen", "Mann",  140000,345000, "pro mikro l");
        bwks[11] = new Blutwertkategorie("Thrombo", "Thrombozyten", "Anzahl der Blutplättchen (Thrombozyten","Erwachsen", "Frau",  140000,345000, "pro mikro l");
        bwks[12] = new Blutwertkategorie("HKT", "Hämatokritwert", "Der Hämatokritwert gibt den Anteil der zellulären Bestandteile am Volumen des Bluts an","Kind","Mann",31,39.8,"Anteil in %");
        bwks[13] = new Blutwertkategorie("HKT", "Hämatokritwert", "Der Hämatokritwert gibt den Anteil der zellulären Bestandteile am Volumen des Bluts an","Kind","Frau",31.2,39.5,"Anteil in %");
        bwks[14] = new Blutwertkategorie("HKT", "Hämatokritwert", "Der Hämatokritwert gibt den Anteil der zellulären Bestandteile am Volumen des Bluts an","Erwachsen","Mann",40,52,"Anteil in %");
        bwks[15] = new Blutwertkategorie("HKT", "Hämatokritwert", "Der Hämatokritwert gibt den Anteil der zellulären Bestandteile am Volumen des Bluts an","Erwachsen","Frau",37,48,"Anteil in %");
        bwks[16] = new Blutwertkategorie("HB", "Hämoglobin", "Konzentration des roten Blutfarbstoffs (Hämogloblin)","Kind", "Mann",  10.2,13.4,"g pro dl");
        bwks[17] = new Blutwertkategorie("HB", "Hämoglobin", "Konzentration des roten Blutfarbstoffs (Hämogloblin)","Kind", "Frau",  10.2,13.4,"g pro dl");
        bwks[18] = new Blutwertkategorie("HB", "Hämoglobin", "Konzentration des roten Blutfarbstoffs (Hämogloblin)","Erwachsen", "Mann",  12,16,"g pro dl");
        bwks[19] = new Blutwertkategorie("HB", "Hämoglobin", "Konzentration des roten Blutfarbstoffs (Hämogloblin)","Erwachsen", "Frau",  13.5,17,"g pro dl");
        bwks[20] = new Blutwertkategorie("MCH", "Mean Corpusolar Hemoglobin Wert", "Der MCH Blutwert stellt das mittlere Gehalt eines roten Blutkörperchens (Erythrozyten) an dem roten Blutfarbstoff Hämoglobin dar", "Kind", "Mann", 23.7,29.2,"pg");
        bwks[21] = new Blutwertkategorie("MCH", "Mean Corpusolar Hemoglobin Wert", "Der MCH Blutwert stellt das mittlere Gehalt eines roten Blutkörperchens (Erythrozyten) an dem roten Blutfarbstoff Hämoglobin dar", "Kind", "Frau", 23.7,29.5,"pg");
        bwks[22] = new Blutwertkategorie("MCH", "Mean Corpusolar Hemoglobin Wert", "Der MCH Blutwert stellt das mittlere Gehalt eines roten Blutkörperchens (Erythrozyten) an dem roten Blutfarbstoff Hämoglobin dar", "Erwachsen", "Mann", 28,34,"pg");
        bwks[23] = new Blutwertkategorie("MCH", "Mean Corpusolar Hemoglobin Wert", "Der MCH Blutwert stellt das mittlere Gehalt eines roten Blutkörperchens (Erythrozyten) an dem roten Blutfarbstoff Hämoglobin dar", "Erwachsen", "Frau", 28,34,"pg");
        bwks[24] = new Blutwertkategorie("MCHC", "Mean Corpulolar Hemoglobin Concentration", "Als MCHC bezeichnet man in der Labormedizin die durchschnittliche (mittlere korpuskuläre) Hämoglobinkonzentration der roten Blutkörperchen oder Erythrozyten, die als Maß für die Sauerstofftransportfähigkeit des Blutes dient", "Kind", "Mann",32,34.9,"g pro dl" );
        bwks[25] = new Blutwertkategorie("MCHC", "Mean Corpulolar Hemoglobin Concentration", "Als MCHC bezeichnet man in der Labormedizin die durchschnittliche (mittlere korpuskuläre) Hämoglobinkonzentration der roten Blutkörperchen oder Erythrozyten, die als Maß für die Sauerstofftransportfähigkeit des Blutes dient", "Kind", "Frau",31.8,34.6,"g pro dl" );
        bwks[26] = new Blutwertkategorie("MCHC", "Mean Corpulolar Hemoglobin Concentration", "Als MCHC bezeichnet man in der Labormedizin die durchschnittliche (mittlere korpuskuläre) Hämoglobinkonzentration der roten Blutkörperchen oder Erythrozyten, die als Maß für die Sauerstofftransportfähigkeit des Blutes dient", "Erwachsen", "Mann",33,36,"g pro dl" );
        bwks[27] = new Blutwertkategorie("MCHC", "Mean Corpulolar Hemoglobin Concentration", "Als MCHC bezeichnet man in der Labormedizin die durchschnittliche (mittlere korpuskuläre) Hämoglobinkonzentration der roten Blutkörperchen oder Erythrozyten, die als Maß für die Sauerstofftransportfähigkeit des Blutes dient", "Erwachsen", "Frau",33,36,"g pro dl" );
        bwks[28] = new Blutwertkategorie("MCV", "Mean Cell Volume", "Das mittlere Erythrozytenvolumen, kurz MCV, gibt die mittlere Zellgröße der roten Blutkörperchen (Erythrozyten) an", "Kind", "Mann", 71.3,86.1,"fl");
        bwks[29] = new Blutwertkategorie("MCV", "Mean Cell Volume", "Das mittlere Erythrozytenvolumen, kurz MCV, gibt die mittlere Zellgröße der roten Blutkörperchen (Erythrozyten) an", "Kind", "Mann", 72.3,87.6,"fl");
        bwks[30] = new Blutwertkategorie("MCV", "Mean Cell Volume", "Das mittlere Erythrozytenvolumen, kurz MCV, gibt die mittlere Zellgröße der roten Blutkörperchen (Erythrozyten) an", "Kind", "Mann", 85,95,"fl");
        bwks[31] = new Blutwertkategorie("MCV", "Mean Cell Volume", "Das mittlere Erythrozytenvolumen, kurz MCV, gibt die mittlere Zellgröße der roten Blutkörperchen (Erythrozyten) an", "Kind", "Mann", 85,95,"fl");

        for(int i = 0; i<bwks.length;i++){
            dbHelper.addBlutwertkategorie(bwks[i]);
        }
    }


}
