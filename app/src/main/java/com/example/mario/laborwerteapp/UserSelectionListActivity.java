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

public class UserSelectionListActivity extends Activity {

    MyDBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection_list);
        dbHelper = new MyDBHelper(this, null, null, 1);


        ListView listView = (ListView) findViewById(R.id.listView_User);




        Cursor c = dbHelper.getAllDataUser();
        if(c.getCount() == 0) {
            showMessage("Error"," Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID :"+c.getString(0)+"\n");
            buffer.append("Vorname :"+c.getString(1)+"\n");
            buffer.append("Nachname :"+c.getString(2)+"\n");
            buffer.append("Email :"+c.getString(3)+"\n");
            buffer.append("Geburtsdatum :"+c.getString(4)+"\n");
            buffer.append("Geschlecht: "+c.getString(5)+"\n\n");
        }

        String data = buffer.toString();
        final String [] trennzeichen = data.split("\n\n");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,trennzeichen);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String k = trennzeichen[position].substring(trennzeichen[position].indexOf("I")+4,trennzeichen[position].indexOf("\n")) ;
                int ue_user = Integer.parseInt(k);
                GlobalVariables globalvariables = (GlobalVariables) getApplicationContext();
                globalvariables.setSelected_userID(ue_user);
                Toast.makeText(UserSelectionListActivity.this, "Es wurde ausgewählt:\n"+trennzeichen[position], Toast.LENGTH_SHORT ).show();

            }
        });

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
