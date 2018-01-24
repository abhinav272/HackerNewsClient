package com.abhinav.hackernewsclient.network.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

import static com.google.gson.stream.JsonToken.NULL;

/**
 * Created by appinventiv on 23/1/18.
 */

public final class EpochDateTypeAdapter extends TypeAdapter<Date> {

    private static final TypeAdapter<Date> epochDateTypeAdapter = new EpochDateTypeAdapter();

    private EpochDateTypeAdapter() {
    }

    public static TypeAdapter<Date> getEpochDateTypeAdapter() {
        return epochDateTypeAdapter;
    }

    @Override
    public Date read(final JsonReader in)
            throws IOException {
        return in.peek() != NULL ? new Date(in.nextLong() * 1000) : null;
    }

    @Override
    public void write(final JsonWriter out, final Date value)
            throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getTime()/1000);
        }
    }
}
