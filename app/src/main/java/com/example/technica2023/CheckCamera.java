package com.example.technica2023;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.hardware.camera2.CameraManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CheckCamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_camera);

        if (cameraAvailable()) {

        }
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


    public boolean cameraAvailable () {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager.registerAvailabilityCallback(new CameraManager.AvailabilityCallback() {
                TextView cameraStatus = findViewById(R.id.camera);
                @Override
                public void onCameraAvailable(String cameraId) {
                    super.onCameraAvailable(cameraId);
                    cameraStatus.setText("Your camera is not being used by another app!");
                    // code here?

                }

                @Override
                public void onCameraUnavailable(String cameraId) {
                    super.onCameraUnavailable(cameraId);
                    cameraStatus.setText("Your camera is currently being used by another app.\n" +
                            "Check the list of apps below to figure out which app might be using " +
                            "your camera.");

                    //

                }
            }, new Handler(Looper.myLooper()));
        }
        try {
            String[] ids = manager.getCameraIdList();
            for (String i : ids) {
            }
        } catch(Exception e) { // CameraAccessException.CAMERA_IN_USE
            return false;
        }
        return true;

    }
}
