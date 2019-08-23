package com.ngopidevteam.pranadana.metime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ngopidevteam.pranadana.metime.Util.APIClient;
import com.ngopidevteam.pranadana.metime.Util.APIInterface;
import com.ngopidevteam.pranadana.metime.Util.PrefConfig;
import com.ngopidevteam.pranadana.metime.fragment.LoginFragment;
import com.ngopidevteam.pranadana.metime.fragment.RegisterFragment;

public class LoginRegister extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener, History.OnLogoutListener {

    public static PrefConfig prefConfig;
    public static APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        prefConfig = new PrefConfig(this);
        apiInterface = APIClient.getApiClient().create(APIInterface.class);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }

            if (prefConfig.readLoginStatus()){
                Intent masuk = new Intent(this, History.class);
                startActivity(masuk);
                finish();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String nama, String userId) {
        prefConfig.writeName(nama);
//        prefConfig.writeUsername(username);
        prefConfig.writeUserId(userId);
        Intent masuk = new Intent(this, History.class);
        startActivity(masuk);
        finish();
    }

    @Override
    public void logoutPerformed() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
