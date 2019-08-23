package com.ngopidevteam.pranadana.metime.Util;

import com.ngopidevteam.pranadana.metime.LoginRegister;
import com.ngopidevteam.pranadana.metime.R;

public class APIMT {
    public static String urlData(String id){
        final String URL_DATA = "http://metime.ngopidevteam.com/transaksi_mt.php?id=" + id;
        return URL_DATA;
    }

}
