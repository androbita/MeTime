package com.ngopidevteam.pranadana.metime.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ngopidevteam.pranadana.metime.History;
import com.ngopidevteam.pranadana.metime.LoginRegister;
import com.ngopidevteam.pranadana.metime.Model.User;
import com.ngopidevteam.pranadana.metime.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView regText;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    OnLoginFormActivityListener loginFormActivityListener;


    public interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String nama, String userId);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        regText = view.findViewById(R.id.text_register);
        etUsername = view.findViewById(R.id.et_username);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Call<User> call = LoginRegister.apiInterface.performLogin(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    LoginRegister.prefConfig.writeLoginStatus(true);
//                    loginFormActivityListener.performLogin(response.body().getNama());
                    loginFormActivityListener.performLogin(response.body().getNama(), response.body().getUserId());
//                    LoginRegister.prefConfig.displayToast("anda login sebagai " + LoginRegister.prefConfig.readUsername());

                    LoginRegister.prefConfig.displayToast("anda login sebagai " + LoginRegister.prefConfig.readName());
                }else if (response.body().getResponse().equals("failed")){
                    LoginRegister.prefConfig.displayToast("Login Gagal... Silahkan coba lagi");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        etUsername.setText("");
        etPassword.setText("");
    }
}
