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

    @Test
    public void ShortStationInfo_equals_should_be_true() {
        final ShortStationInfo shortStationInfo1 = new ShortStationInfo.Builder().setId("id1").build();
        final ShortStationInfo shortStationInfo2 = new ShortStationInfo.Builder().setId("id1").build();
        assertEquals(shortStationInfo1, shortStationInfo2);
    }

    @Test
    public void ShortStationInfo_equals_should_be_false() {
        final ShortStationInfo shortStationInfo1 = new ShortStationInfo.Builder().setId("id1").build();
        final ShortStationInfo shortStationInfo2 = new ShortStationInfo.Builder().setId("id2").build();
        assertNotEquals(shortStationInfo1, shortStationInfo2);
        assertNotEquals(shortStationInfo1, null);
        assertNotEquals(shortStationInfo1, new Object());
    }
}