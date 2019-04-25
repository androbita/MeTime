package com.ngopidevteam.pranadana.metime;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ngopidevteam.pranadana.metime.fragment.HistoryWH;
import com.ngopidevteam.pranadana.metime.fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AmbilJam extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView textMulai;
    private TextView textSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambil_jam);

        textMulai = findViewById(R.id.text_mulai);
        textSelesai = findViewById(R.id.text_selesai);
        Button btnMulai = findViewById(R.id.btn_mulai);
        Button btnSelesai = findViewById(R.id.btn_selesai);

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time Picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeMulai(c);
        updateTimeSelesai(c);
    }

    private void updateTimeMulai(Calendar c) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction t = fragmentManager.beginTransaction();
        final HistoryWH historyWH = new HistoryWH();

        String waktuMulai = "";
        waktuMulai += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        TextView textMulai = findViewById(R.id.text_mulai);
        textMulai.setText(waktuMulai);
    }

    private void updateTimeSelesai(Calendar c) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction t = fragmentManager.beginTransaction();
        final HistoryWH historyWH = new HistoryWH();

        String waktuSelesai = "";
        waktuSelesai += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        TextView textSelesai = findViewById(R.id.text_selesai);
        textSelesai.setText(waktuSelesai);
    }

    public void timeSelected(){

    }
}
