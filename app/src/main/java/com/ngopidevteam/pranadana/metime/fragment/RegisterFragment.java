package com.ngopidevteam.pranadana.metime.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ngopidevteam.pranadana.metime.LoginRegister;
import com.ngopidevteam.pranadana.metime.Model.User;
import com.ngopidevteam.pranadana.metime.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextInputEditText nama, tanggal, pekerjaan, email, username, password, konfirmPass;
    private Button btnReg;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        nama = view.findViewById(R.id.etNama);
        tanggal = view.findViewById(R.id.etTanggal);
        pekerjaan = view.findViewById(R.id.etPekerjaan);
        email = view.findViewById(R.id.etEmail);
        username = view.findViewById(R.id.etUsername);
        password = view.findViewById(R.id.etPassword);
        konfirmPass = view.findViewById(R.id.etKonfirm);

        btnReg = view.findViewById(R.id.btnDaftar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();

            }
        });
        return view;
    }

    public void performRegister(){
        String strNama = nama.getText().toString();
        String strTanggal = tanggal.getText().toString();
        String strPekerjaan = pekerjaan.getText().toString();
        String strEmail = email.getText().toString();
        String strUsername = username.getText().toString();
        final String strPass = password.getText().toString();
        final String strKonfirm = konfirmPass.getText().toString();

        Call<User> call = LoginRegister.apiInterface.performRegistration(strNama, strTanggal, strPekerjaan, strEmail, strUsername, strPass);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (strPass.equalsIgnoreCase(strKonfirm)){
                    if (response.body().getResponse().equals("ok")) {
                        LoginRegister.prefConfig.displayToast("Registrasi Berhasil");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new LoginFragment()).addToBackStack(null).commit();
                    } else if (response.body().getResponse().equals("exist")) {
                        LoginRegister.prefConfig.displayToast("Username Sudah Ada");
                    } else if (response.body().getResponse().equals("error")) {
                        LoginRegister.prefConfig.displayToast("Registrasi Gagal");
                    }
                }else {
                    LoginRegister.prefConfig.displayToast("Password tidak sesuai");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        nama.setText("");
        tanggal.setText("");
        pekerjaan.setText("");
        email.setText("");
        username.setText("");
        password.setText("");
        konfirmPass.setText("");
    }

}
