package com.ngopidevteam.pranadana.metime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class PilihMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_menu);

        Thread t =new Thread(){
            @Override
            public void run(){
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView jamJalan = findViewById(R.id.jamJalan);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nHH:mm:ss a");
                                String jamString = sdf.format(date);
                                jamJalan.setText(jamString);
                            }
                        });
                    }
                }catch (InterruptedException e){

                }
            }
        };
        t.start();

        Button btnWH = findViewById(R.id.btnWH);
        Button btnFT = findViewById(R.id.btnFT);
        Button btnMT = findViewById(R.id.btnMT);
        Button btnHistory = findViewById(R.id.btnHistory);

        btnWH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ambilJam = new Intent(getApplicationContext(), AmbilJam.class);
                startActivity(ambilJam);
            }
        });

        btnFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ambilJam = new Intent(getApplicationContext(), AmbilJam.class);
                startActivity(ambilJam);
            }
        });

        btnMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ambilJam = new Intent(getApplicationContext(), AmbilJam.class);
                startActivity(ambilJam);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(getApplicationContext(), History.class);
                startActivity(history);
//                Toast.makeText(getApplicationContext(), "Layout belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
