package com.example.technica2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aboutPage(View v) {
        Intent myIntent = new Intent(this,AboutPage.class);
        this.startActivity(myIntent);
    }

    public void checkCamera(View v) {
        Intent myIntent = new Intent(this, CheckCamera.class);
        this.startActivity(myIntent);
    }

    public void checkPorts(View v) {
        Intent myIntent = new Intent(this, Ports.class);
        this.startActivity(myIntent);
    }
    public void checkPermissions(View v) {
        Intent myIntent = new Intent(this, CheckPermissions.class);
        this.startActivity(myIntent);
    }

}