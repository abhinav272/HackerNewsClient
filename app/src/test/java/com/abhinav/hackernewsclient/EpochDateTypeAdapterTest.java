package com.abhinav.hackernewsclient;

import com.google.gson.TypeAdapter;
import org.junit.Test;
import java.io.IOException;
import java.util.Date;
import static com.abhinav.hackernewsclient.data.network.adapter.EpochDateTypeAdapter.getEpochDateTypeAdapter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by appinventiv on 3/2/18.
 */

public final class EpochDateTypeAdapterTest {

    @Test
    public void testRead()
            throws IOException {
        final TypeAdapter<Date> unit = getEpochDateTypeAdapter();
        assertThat(unit.fromJson("0"), is(new Date(0)));
        assertThat(unit.fromJson("1488929283"), is(new Date(1488929283000L)));
        assertThat(unit.fromJson("null"), nullValue());
    }

    @Test
    public void testWrite() {
        final TypeAdapter<Date> unit = getEpochDateTypeAdapter();
        assertThat(unit.toJson(new Date(0)), is("0"));
        assertThat(unit.toJson(new Date(1488929283000L)), is("1488929283"));
        assertThat(unit.toJson(null), is("null"));
    }

}
