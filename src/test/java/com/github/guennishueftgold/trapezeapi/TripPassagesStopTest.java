package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripPassagesStopTest {

    private final static TypeAdapter<TripPassageStop> adapter = new TripPassageStop.Converter();
    private final static LocalTime PLANNED_TIME = LocalTime.fromMillisOfDay(1000);
    private final static LocalTime ACTUAL_TIME = LocalTime.fromMillisOfDay(10000);

    public static final TripPassageStop.Builder createSample(int idx) {
        return new TripPassageStop.Builder()
                .setName("name_" + idx)
                .setShortName("short_name_" + idx)
                .setStatus(idx)
                .setId("id_" + idx)
                .setStopSeqNum(idx)
                .setActualTime(ACTUAL_TIME.plusMillis(idx))
                .setPlannedTime(PLANNED_TIME.plusMillis(idx));
    }
    @Test
    public void typeadapter_read_full_information() throws Exception {
        TripPassageStop input = createSample(1).build();
        TripPassageStop output = adapter.fromJson(adapter.toJson(input));
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
