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

import com.ngopidevteam.pranadana.metime.Model.User;
import com.ngopidevteam.pranadana.metime.fragment.LoginFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmbilJam extends AppCompatActivity {
    private TextView textTglMulai;
    private TextView textJamMulai;
    private TextView textTglSelesai;
    private TextView textJamSelesai;
    private TextView textJenis;
    private TimePickerDialog timePicker;

    String tglMulai = "";
    String jamMulai = "";
    String tglSelesai = "";
    String jamSelesai = "";

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
        textJenis = findViewById(R.id.txt_jenis);


        RelativeLayout rl = findViewById(R.id.layoutAmbilJam);

        switch (numTab) {
            case "0":
                rl.setBackgroundResource(R.drawable.working);
                textTglMulai.setText("Working Hour");
                textJenis.setText("working_hour");
                break;
            case "1":
                rl.setBackgroundResource(R.drawable.family);
                textTglMulai.setText("Family Time");
                textJenis.setText("family_time");
                break;
            case "2":
                rl.setBackgroundResource(R.drawable.metime);
                textTglMulai.setText("Me Time");
                textJenis.setText("me_time");
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
                performStartLock();
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
        textTglSelesai.setText(null);
        textJamSelesai.setText("Pilih Waktu Selesai");
        Calendar calendar = Calendar.getInstance();
        timePicker = new TimePickerDialog(this, onTimeSelesai, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePicker.setTitle("Pilih Waktu");
        timePicker.show();
    }

    private void openTimeMulai() {
        textTglMulai.setText(null);
        textJamMulai.setText("Pilih Waktu Mulai");
        Calendar calendar = Calendar.getInstance();
        Calendar calset = (Calendar) calendar.clone();
//        timePicker = new TimePickerDialog(this, onTimeMulai, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
//        timePicker.setTitle("Pilih Waktu");
//        timePicker.show();
        Date c = calset.getTime();
//        long m = calset.getTimeInMillis();
//        Date minute = new Date(m + (10 * 6000) );

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dt = new SimpleDateFormat("HH.mm");
        textTglMulai.setText(df.format(c));
        textJamMulai.setText(dt.format(c));
    }

    TimePickerDialog.OnTimeSetListener onTimeMulai = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calnow = Calendar.getInstance();
            Calendar calset = (Calendar) calnow.clone();
            calset.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calset.set(Calendar.MINUTE, minute);
            calset.set(Calendar.SECOND, 0);
            calset.set(Calendar.MILLISECOND, 0);
            if (calset.compareTo(calnow) <= 0) {
//                calset.add(Calendar.DATE,0);
                Toast.makeText(AmbilJam.this, "Maaf, penjadwalan untuk esok hari belum tersedia", Toast.LENGTH_SHORT).show();

            } else if (calset.compareTo(calnow) > 0) {
                Log.i("hasil", ">0");
                setJamMulai(calset);
            }
//            setJamMulai(calnow);
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSelesai = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calnow = Calendar.getInstance();
            Calendar calset = (Calendar) calnow.clone();
            calset.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calset.set(Calendar.MINUTE, minute);
            calset.set(Calendar.SECOND, 0);
            calset.set(Calendar.MILLISECOND, 0);
            if (calset.compareTo(calnow) <= 0) {
//                calset.add(Calendar.DATE,0);
                Toast.makeText(AmbilJam.this, "Maaf, penjadwalan untuk esok hari belum tersedia", Toast.LENGTH_SHORT).show();
            } else if (calset.compareTo(calnow) > 0) {
                Log.i("hasil", ">0");
                setJamSelesai(calset);
            }

        }
    };

    private void setJamMulai(Calendar calset) {

        tglMulai = DateFormat.getDateInstance(DateFormat.SHORT).format(calset.getTime());
        jamMulai = DateFormat.getTimeInstance(DateFormat.SHORT).format(calset.getTime());

        textTglMulai.setText("" + tglMulai);
        textJamMulai.setText("" + jamMulai);
    }

    private void setJamSelesai(Calendar calset) {

        jamSelesai = DateFormat.getTimeInstance(DateFormat.SHORT).format(calset.getTime());
        tglSelesai = DateFormat.getDateInstance(DateFormat.SHORT).format(calset.getTime());

        textJamSelesai.setText("" + jamSelesai);
        textTglSelesai.setText("" + tglSelesai);
    }

    public void performStartLock() {

        String strTanggal = textTglSelesai.getText().toString();
        String strJamMulai = textJamMulai.getText().toString();
        String strJamSelesai = textJamSelesai.getText().toString();
        String strJenis = textJenis.getText().toString();
        String strUserId = LoginRegister.prefConfig.readUserId();


        Call<User> call = LoginRegister.apiInterface.performTimeTransaction(strTanggal, strJamMulai, strJamSelesai, strJenis, strUserId);

        if (textTglMulai.getText().equals("Pilih Waktu Mulai")) {
            Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Mulai", Toast.LENGTH_SHORT).show();
        } else if (textTglSelesai.getText().equals("Pilih Waktu Selesai")) {
            Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Selesai", Toast.LENGTH_SHORT).show();
        } else if (textJamMulai.getText().equals("Pilih Waktu Mulai")) {
            Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Mulai", Toast.LENGTH_SHORT).show();
        }else if (textJamSelesai.getText().equals("Pilih Waktu Selesai")) {
            Toast.makeText(AmbilJam.this, "Silahkan Masukkan Waktu Selesai", Toast.LENGTH_SHORT).show();
        }else if (textJamMulai.getText().equals(textJamSelesai.getText())){
            Toast.makeText(AmbilJam.this, "Waktu mulai dan Waktu Selesai tidak boleh sama", Toast.LENGTH_SHORT).show();
        }else{
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("ok")) {
                        Intent intentLock = new Intent(AmbilJam.this, LockApp.class);
                        intentLock.putExtra("jamMulai", textJamMulai.getText());
                        intentLock.putExtra("jamSelesai", textJamSelesai.getText());
                        intentLock.putExtra("jenis", textJenis.getText());
                        startActivity(intentLock);
                        finish();
                    } else if (response.body().getResponse().equals("exist")) {
                        LoginRegister.prefConfig.displayToast("Username Sudah Ada");
                    } else if (response.body().getResponse().equals("error")) {
                        LoginRegister.prefConfig.displayToast("Gagal");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}