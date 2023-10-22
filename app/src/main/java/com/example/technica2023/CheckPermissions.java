package com.example.technica2023;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CheckPermissions extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_permissions);
        TextView listPerms = findViewById(R.id.permissions);

        listPerms.setText(getPermissions().toString());
    }

    public void back(View v){
        finish();
    }

    public StringBuffer getPermissions() {
        StringBuffer appNameAndPermissions = new StringBuffer();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                appNameAndPermissions.append(packageInfo.packageName + "*******:\n");

                //Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        Log.d("test", requestedPermissions[i]);
                        appNameAndPermissions.append(requestedPermissions[i] + "\n");
                    }
                }
                appNameAndPermissions.append("\n");
                appNameAndPermissions.append("-------------\n\nDescription:\n");
                appNameAndPermissions.append(getDescription(applicationInfo.packageName));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return appNameAndPermissions;
    }


    public CharSequence getDescription(String packageName) throws PackageManager.NameNotFoundException {
        PackageManager pm = getPackageManager();
        ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
        CharSequence desc = appInfo.loadDescription(pm);

        return desc;
    }

}
