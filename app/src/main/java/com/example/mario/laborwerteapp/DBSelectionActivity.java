package com.example.mario.laborwerteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DBSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbselection);
    }

    public void toEintragDBClicked(View view) {
        openEintragDBClicked();
    }

    public void toBlutDBClicked(View view) {
        openBlutDBClicked();
    }

    public void toUserDBClicked(View view) {
        openUserDBClicked();
    }

    public void openEintragDBClicked(){
        Intent intent = new Intent(this, dbTestActivity2.class);
        startActivity(intent);
    }

    public void openUserDBClicked() {
        Intent intent = new Intent(this, UserSelectionListActivity.class);
        startActivity(intent);
    }

    public void openBlutDBClicked(){
        Intent intent = new Intent(this, dbTestActivity.class);
        startActivity(intent);
    }
}
