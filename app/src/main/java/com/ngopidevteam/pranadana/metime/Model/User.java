package com.ngopidevteam.pranadana.metime.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String response;

    @SerializedName("id")
    private String userId;

    @SerializedName("nama")
    private String nama;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("tanggal_lahir")
    private String tanggalLahir;

    @SerializedName("pekerjaan")
    private String pekerjaan;

    @SerializedName("email")
    private String email;

    public String getResponse(){
        return response;
    }

    public String getNama(){
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}
