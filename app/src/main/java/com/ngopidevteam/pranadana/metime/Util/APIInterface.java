package com.ngopidevteam.pranadana.metime.Util;

import com.ngopidevteam.pranadana.metime.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("register.php")
    Call<User> performRegistration(
            @Query("nama") String nama,
            @Query("tanggal_lahir") String tanggalLahir,
            @Query("pekerjaan") String pekerjaan,
            @Query("email") String email,
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("login.php")
    Call<User> performLogin(
//            @Query("id") String userId,
            @Query("username") String username,
            @Query("password") String password
    );

    @GET ("insert_transaksi.php")
    Call<User> performTimeTransaction(
            @Query("tanggal") String tanggal,
            @Query("jam_mulai") String jamMulai,
            @Query("jam_selesai") String jamSelesai,
            @Query("jenis_kualitas") String jenis,
            @Query("user_id") String userId
    );
}
