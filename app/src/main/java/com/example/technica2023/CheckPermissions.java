package com.example.technica2023;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class CheckPermissions extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_permissions);
        TextView listPerms = findViewById(R.id.permissions);
        listPerms.setMovementMethod(new ScrollingMovementMethod());

        HashMap<String, ArrayList<ArrayList<ArrayList<String>>>> perms = getPermissions();

        listPerms.append("\n");

        ArrayList<ArrayList<ArrayList<String>>> values;
        ArrayList<Integer> outliers;
        listPerms.append(perms.keySet().toString());
        for (String desc : perms.keySet()) {
            values = perms.get(desc);
            outliers = getOutlierIndexes(values);

            listPerms.append(desc + "\n");
            listPerms.append("Average permissions: " + outliers.get(0) + "\n");
            listPerms.append("****************\n");

            for (int i: outliers.subList(1,outliers.size())) {
                listPerms.append("------\n" + values.get(i).get(0) + "\nNum Permissions: " + values.get(i).get(1).size() + "\n\n");
            }
        }
    }

    public ArrayList<Integer> getOutlierIndexes(ArrayList<ArrayList<ArrayList<String>>> arr){
        ArrayList<Integer> lens = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        Integer size;
        Integer total = 0;

        for (ArrayList<ArrayList<String>> item : arr) {
            size = item.get(1).size();
            lens.add(size);
            total = total + size;
        }

        Integer average = total / lens.size();

        res.add(average);

        Integer index;
        for (index = 0; index < lens.size(); index++) {
            if (lens.get(index) >= average + 9) {
                res.add(index);
            }
        }

        return res;
    }


    public void back(View v){
        finish();
    }


    // Key is in the format: name, package name, pakcage anme,  description
    public HashMap<String, ArrayList<ArrayList<ArrayList<String>>>> getPermissions() {
        HashMap<String, ArrayList<ArrayList<ArrayList<String>>>> dict = new HashMap<String, ArrayList<ArrayList<ArrayList<String>>>>();

        StringBuffer appNameAndPermissions = new StringBuffer();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {

            ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
            ArrayList<String> desc = new ArrayList<String>();
            ArrayList<String> perms = new ArrayList<String>();

            try {


                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

                desc.add(applicationInfo.name);
                desc.add(applicationInfo.packageName);
                desc.add(packageInfo.packageName);

                //Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        perms.add(requestedPermissions[i]);
                        //Log.d("test", requestedPermissions[i]);
                        //appNameAndPermissions.append(requestedPermissions[i] + "\n");
                    }
                }

                arr.add(desc);
                arr.add(perms);


                String description = (String) getDescription(applicationInfo.packageName);
                System.out.println(description);
                if (! dict.containsKey(description)) {
                    ArrayList<ArrayList<ArrayList<String>>> item = new ArrayList<ArrayList<ArrayList<String>>>();
                    item.add(arr);
                    dict.put(description, item);
                } else {
                    dict.get(description).add(arr);
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }


        return dict;
    }


    public CharSequence getDescription(String packageName) throws PackageManager.NameNotFoundException {
        System.out.println(packageName);
        PackageManager pm = getPackageManager();
        ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
        System.out.println(appInfo);

        CharSequence desc = appInfo.loadDescription(pm);

        return desc;
    }

    public void makeGraph(ArrayList<Integer> perms){
        String histogram = "";
        for(int i : perms){
            histogram += "" + i + " ";
            for(int j = i; j > 0; j--){
                histogram += "*";
            }
            histogram += "\n";
        }
        TextView tv = findViewById(R.id.hist);
        tv.setText(histogram);
    }

}
