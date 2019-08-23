package com.ngopidevteam.pranadana.metime;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class AmbilJam extends AppCompatActivity {
    private TextView textTglMulai;
    private TextView textJamMulai;
    private TextView textTglSelesai;
    private TextView textJamSelesai;
    private TimePickerDialog timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambil_jam);

        Intent intentTab = getIntent();
        String numTab = intentTab.getStringExtra("tab");

        textTglMulai = findViewById(R.id.text_tgl_mulai);
        textJamMulai = findViewById(R.id.text_jam_mulai);
        textTglSelesai = findViewById(R.id.text_tgl_selesai);
        textJamSelesai = findViewById(R.id.text_jam_selesai);

        RelativeLayout rl = findViewById(R.id.layoutAmbilJam);

        switch (numTab){
            case "0":
                rl.setBackgroundResource(R.drawable.working);
                textTglMulai.setText("Working Hour");
                break;
            case "1":
                rl.setBackgroundResource(R.drawable.family);
                textTglMulai.setText("Family Time");
                break;
            case "2":
                rl.setBackgroundResource(R.drawable.metime);
                textTglMulai.setText("Me Time");
                break;
        }



        Button btnMulai = findViewById(R.id.btn_mulai);
        Button btnSelesai = findViewById(R.id.btn_selesai);
        Button btnStartLock = findViewById(R.id.btn_start);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeMulai();
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeSelesai();
            }
        });

        btnStartLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textTglMulai.getText().equals("") && textJamMulai.getText().equals("Pilih Waktu Mulai")){
                    Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Mulai", Toast.LENGTH_SHORT).show();
                }else if (textTglSelesai.getText().equals("") && textJamSelesai.getText().equals("Pilih Waktu Selesai")){
                    Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Selesai", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intentLock = new Intent(AmbilJam.this, LockApp.class);
                    intentLock.putExtra("jamMulai", textJamMulai.getText());
                    intentLock.putExtra("jamSelesai", textJamSelesai.getText());
                    startActivity(intentLock);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTglMulai.setText(null);
                textJamMulai.setText("Pilih Waktu Mulai");
                textTglSelesai.setText(null);
                textJamSelesai.setText("Pilih Waktu Selesai");
            }
        });

    }

    private void openTimeSelesai() {
        Calendar calendar = Calendar.getInstance();
        timePicker = new TimePickerDialog(this, onTimeSelesai,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePicker.setTitle("Pilih Waktu");
        timePicker.show();
    }

    private void openTimeMulai() {
        Calendar calendar = Calendar.getInstance();
        timePicker = new TimePickerDialog(this, onTimeMulai,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePicker.setTitle("Pilih Waktu");
        timePicker.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeMulai = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calnow = Calendar.getInstance();
            Calendar calset = (Calendar)calnow.clone();
            calset.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calset.set(Calendar.MINUTE,minute);
            calset.set(Calendar.SECOND,0);
            calset.set(Calendar.MILLISECOND,0);
            if (calset.compareTo(calnow)<=0){
                calset.add(Calendar.DATE,1);
            }else if (calset.compareTo(calnow)>0){
                Log.i("hasil",">0");
            }
            setJamMulai(calset);
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSelesai = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calnow = Calendar.getInstance();
            Calendar calset = (Calendar)calnow.clone();
            calset.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calset.set(Calendar.MINUTE,minute);
            calset.set(Calendar.SECOND,0);
            calset.set(Calendar.MILLISECOND,0);
            if (calset.compareTo(calnow)<=0){
                calset.add(Calendar.DATE,1);
            }else if (calset.compareTo(calnow)>0){
                Log.i("hasil",">0");
            }
            setJamSelesai(calset);
        }
    };

    private void setJamMulai(Calendar calset) {
        String tglMulai = "";
        String jamMulai = "";
        tglMulai += DateFormat.getDateInstance(DateFormat.SHORT).format(calset.getTime());
        jamMulai += DateFormat.getTimeInstance(DateFormat.SHORT).format(calset.getTime());
        textTglMulai.setText(""+tglMulai);
        textJamMulai.setText(""+jamMulai);
    }

    private void setJamSelesai(Calendar calset) {
        String tglSelesai = "";
        String jamSelesai = "";
        jamSelesai += DateFormat.getTimeInstance(DateFormat.SHORT).format(calset.getTime());
        tglSelesai += DateFormat.getDateInstance(DateFormat.SHORT).format(calset.getTime());
        textJamSelesai.setText(""+jamSelesai);
        textTglSelesai.setText(""+tglSelesai);
    }

}
