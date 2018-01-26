package com.abhinav.hackernewsclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by appinventiv on 26/1/18.
 */

public class Formatter {

    public static String formatPublishDate(Date date) {
        return new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault()).format(date);
    }
}
