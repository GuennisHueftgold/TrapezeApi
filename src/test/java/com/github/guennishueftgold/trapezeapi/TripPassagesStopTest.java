package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class TripPassagesStopTest {

    private final static TypeAdapter<TripPassageStop> adapter = new TripPassageStop.Converter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        TripPassageStop input = new TripPassageStop.Builder()
                .setStatus(TripPassageStop.STATUS_DEPARTED)
                .setShortName("shortName")
                .build();
        TripPassageStop output = adapter.fromJson(adapter.toJson(input));
        assertTrue(output.equals(input));
    }

    @Test
    public void typeadapter_check_read_null() throws Exception{
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void typeAdapter_check_write_null() throws Exception{
        assertEquals(adapter.toJson(null),"null");
    }
}
