package com.ngopidevteam.pranadana.metime.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ngopidevteam.pranadana.metime.R;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String nama){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username), nama);
        editor.commit();
    }

    public void writeUsername(String username){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username), username);
        editor.commit();
    }

    public String readName(){
        return sharedPreferences.getString(context.getString(R.string.pref_username), "User");
    }

    public String readUsername(){
        return sharedPreferences.getString(context.getString(R.string.pref_username), "User");
    }

    public void writeUserId(String userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_id), userId);
        editor.commit();
    }

    public String readUserId(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_id), "userId");
    }

    public void displayToast(String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
