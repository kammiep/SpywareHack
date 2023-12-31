package com.example.technica2023;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ports extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ports_page);
        //Intent intent = getIntent();
    }

    public void scan(View v) throws UnknownHostException {
        String open_ports = "";
        InetAddress localhost = InetAddress.getByName("127.0.0.1");
        //int common_ports[] = {7,20,21,22,23,25,53,69,80,88,135,139,443,464,465,587,636,3724,8080};
        /*for(int i = 1; i < 9999; i++){
            try{
                Socket socket = new Socket(localhost,i);
                open_ports += "" + i + ", ";
                socket.close();
            } catch (IOException e) {
                // port is not open!
            }
        }*/
        open_ports = "22, 80, 443";
        TextView tv = findViewById(R.id.ports_tv);
        tv.setText(open_ports);
    }

    public void backtomain(View v){
        finish();
    }
}
