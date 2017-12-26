package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortStationInfoTest {

    private final static TypeAdapter<ShortStationInfo> adapter = new ShortStationInfo.Converter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        ShortStationInfo input = new ShortStationInfo.Builder()
                .setId("random_id")
                .setStopName("stop_name")
                .setStopShortName("stop_short_name")
                .build();
        ShortStationInfo output = adapter.fromJson(adapter.toJson(input));
        assertTrue(output.equals(input));
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void typeAdapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }
}