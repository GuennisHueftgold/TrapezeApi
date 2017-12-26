package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocalTimeTypeAdapterTest {
    private final static TypeAdapter<LocalTime> adapter = new LocalTimeTypeAdapter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        LocalTime localTime = LocalTime.now();
        LocalTime output = adapter.fromJson(adapter.toJson(localTime));
        assertTrue(output.equals(localTime));
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }
}
