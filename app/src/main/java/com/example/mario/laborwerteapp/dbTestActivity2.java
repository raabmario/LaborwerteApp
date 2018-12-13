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

public class dbTestActivity2 extends Activity {

    MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test2);
        dbHelper = new MyDBHelper(this, null, null, 1);


        ListView listView = (ListView) findViewById(R.id.listView_EintragDB);
        Cursor c = dbHelper.getAllDataEintrag();
        if(c.getCount() == 0) {
            showMessage("Error"," Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID :"+c.getString(0)+"\n");
            buffer.append("UID :"+c.getString(1)+"\n");
            buffer.append("BID :"+c.getString(2)+"\n");
            buffer.append("WERT :"+c.getString(3)+"\n");
            buffer.append("DATUM :"+c.getString(4)+"\n\n");

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
