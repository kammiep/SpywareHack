package com.example.technica2023;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CheckCamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_camera);
    }

    public void listApps() {
        PackageManager pm = this.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
        ArrayList<String> stringList = new ArrayList<>();
        for (ResolveInfo r : list) {
            System.out.println("List of Installed Applications:" + r.activityInfo.applicationInfo.loadLabel(pm).toString());
            stringList.add(r.activityInfo.applicationInfo.loadLabel(pm).toString());
        }

        String[] arrayList = (String[]) list.toArray();
        ListView l = findViewById(R.id.list);
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.check_camera,
                arrayList);
        l.setAdapter(arr);


    }
}
