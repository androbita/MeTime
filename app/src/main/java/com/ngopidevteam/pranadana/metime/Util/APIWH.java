package com.ngopidevteam.pranadana.metime.Util;

import com.ngopidevteam.pranadana.metime.LoginRegister;
import com.ngopidevteam.pranadana.metime.R;

public class APIWH {
    public static String urlData(String id){
        final String URL_DATA = "http://metime.ngopidevteam.com/transaksi_wh.php?id=" + id;
        return URL_DATA;
    }

}
