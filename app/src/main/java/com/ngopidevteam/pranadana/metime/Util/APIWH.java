package com.ngopidevteam.pranadana.metime.Util;

import com.ngopidevteam.pranadana.metime.LoginRegister;
import com.ngopidevteam.pranadana.metime.R;

public class APIWH {
    public static String urlData(String username){
        final String URL_DATA = "http://metime.ngopidevteam.com/transaksi_wh.php?username=" + username;
        return URL_DATA;
    }

}
