package com.ngopidevteam.pranadana.metime;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;

public class LockApp extends Activity {

    TextView jamMulai, jamSelesai;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onAttachedToWindow();

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_lock_app);

        jamMulai = findViewById(R.id.jamMulai);
        jamSelesai = findViewById(R.id.jamSelesai);

        Intent intentAmbilJam = getIntent();
        String strJamMulai = intentAmbilJam.getStringExtra("jamMulai");
        String strJamSelesai = intentAmbilJam.getStringExtra("jamSelesai");
        jamMulai.setText(strJamMulai);
        jamSelesai.setText(strJamSelesai);

        Thread t =new Thread(){
            @Override
            public void run(){
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView jamJalan = findViewById(R.id.textJam);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
                                String jamString = sdf.format(date);
                                jamJalan.setText(jamString);

                                String jamSatu = jamSelesai.getText().toString();
                                String jamDua = jamJalan.getText().toString();

                                if (jamSatu.equalsIgnoreCase(jamDua)){
                                    Intent intent = new Intent(LockApp.this, History.class);
                                    startActivity(intent);
                                    KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
                                    final KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
                                    lock.reenableKeyguard();
                                    Toast.makeText(LockApp.this, "Anda Berhenti", Toast.LENGTH_SHORT).show();
                                    btnCancel.setEnabled(true);
                                    finish();
                                }
                            }
                        });
                        Thread.sleep(60000);
                    }
                }catch (InterruptedException e){

                }
            }
        };
        t.start();


        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        final KeyguardManager.KeyguardLock lock= keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();

        jamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCancel.isEnabled() == false) {
                    btnCancel.setVisibility(View.VISIBLE);
                    btnCancel.setEnabled(true);
                }
            }
        });

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setEnabled(false);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockApp.this, History.class);
                startActivity(intent);
                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
                final KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
                lock.reenableKeyguard();
                Toast.makeText(LockApp.this, "Anda Berhenti", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }


    @Override
    protected void onUserLeaveHint() {
        if (btnCancel.isEnabled()) {
            if (btnCancel.isClickable()) {
                Intent intent = new Intent(LockApp.this, History.class);
                startActivity(intent);
                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
                final KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
                lock.reenableKeyguard();
            }
        }else {
            Intent intent = new Intent(this, LockApp.class);
            intent.putExtra("jamMulai", jamMulai.getText());
            intent.putExtra("jamSelesai", jamSelesai.getText());
            startActivity(intent);
            finish();
            Toast.makeText(this, "Anda Menekan Tombol Home", Toast.LENGTH_SHORT).show();
        super.onUserLeaveHint();
        }
    }

    @Override
    public void onAttachedToWindow() {
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);

    }
}
