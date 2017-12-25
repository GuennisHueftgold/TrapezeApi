package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TripPassagesStopTest {

    private final static TypeAdapter<TripPassageStop> adapter = new TripPassageStop.Converter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        TripPassageStop input = new TripPassageStop.Builder()
                .setStatus(TripPassageStop.STATUS_DEPARTED)
                .setShortName("shortName")
                .build();
        TripPassageStop output = adapter.fromJson(adapter.toJson(input));
        System.out.println(input.toString());
        System.out.println(output.toString());
        assertTrue(output.equals(input));
    }
}
