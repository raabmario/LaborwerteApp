package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class dbTestActivity extends Activity {

    MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        dbHelper = new MyDBHelper(this, null, null, 1);


        ListView listView = (ListView) findViewById(R.id.listView_BlutDB);
        Cursor c = dbHelper.getAllDataBlutwertkategorie();

        if(c.getCount() == 0) {
            showMessage("Error"," Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID :"+c.getString(0)+"\n");
            buffer.append("Abk :"+c.getString(1)+"\n");
            buffer.append("Name :"+c.getString(2)+"\n");
            buffer.append("Beschreibung :"+c.getString(3)+"\n");
            buffer.append("AgeGroup :"+c.getString(4)+"\n");
            buffer.append("Gender: "+c.getString(5)+"\n");
            buffer.append("Norm Low: "+c.getDouble(6)+"\n");
            buffer.append("Norm High: "+c.getDouble(7)+"\n");
            buffer.append("Einheit: "+c.getString(8)+"\n\n");
        }
        String data = buffer.toString();
        final String [] trennzeichen = data.split("\n\n");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,trennzeichen);
        listView.setAdapter(adapter);


    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
