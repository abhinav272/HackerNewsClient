package com.abhinav.hackernewsclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by appinventiv on 26/1/18.
 */

public class Formatter {

    /*
    * Thu Feb 01 17:54:35 GMT+05:30 2018
    * formatted as Thu, Feb 1, '18
    * */
    public static String formatPublishDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(date);
        } else return null;
    }
}
