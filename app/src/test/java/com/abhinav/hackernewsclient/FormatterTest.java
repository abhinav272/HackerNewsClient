package com.abhinav.hackernewsclient;

import com.abhinav.hackernewsclient.utils.Formatter;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by appinventiv on 3/2/18.
 */

public class FormatterTest {

    @Test
    public void formatPublishDateTest() {
        assertEquals("Thu, Feb 1, '18", Formatter.formatPublishDate(new Date(1517487875000L)));
        assertEquals(null, Formatter.formatPublishDate(null));
    }
}
