package org.wifry.fooddelivery.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author wtuco
 */
public class TokenUtil {

    private TokenUtil() {

    }

    public static String getToken(String mail, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(System.currentTimeMillis());
        String time = sdf.format(date);
        String mailPassTime = mail + ":" + password + ":" + time;
        String token1 = "_Basic_" + java.util.Base64.getEncoder().encodeToString(mailPassTime.getBytes());
        System.out.println("token : " + token1);
        return token1;

    }

    private boolean checkToken(String token) {
        return true;
    }

    void convert(String decode) {
        String s = "_Basic_" + Arrays.toString(java.util.Base64.getDecoder().decode(decode));
        System.out.println(s);
    }

}