package com.example.technica2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aboutPage(View v){
        Intent myIntent = new Intent(this,AboutPage.class);
        this.startActivity(myIntent);
    }

    public void checkCamera(View v) {
        Intent myIntent = new Intent(this, CheckCamera.class);
        this.startActivity(myIntent);
    }
}