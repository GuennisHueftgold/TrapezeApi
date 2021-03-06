package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void TypeAdapter_stop_seq_num_should_be_readable_as_string() throws IOException {
        TripPassageStop tripPassageStop = adapter.fromJson("{\"" + TripPassageStop.Converter.STOP_SEQ_NUM + "\":\"2\"}");
        assertEquals(tripPassageStop.getStopSeqNum(), 2);
    }

    @Test
    public void TripPassageStop_equal_should_be_true() {
        TripPassageStop tripPassageStop1 = createSample(1).build();
        TripPassageStop tripPassageStop2 = createSample(1).build();
        assertEquals(tripPassageStop1, tripPassageStop1);
        assertEquals(tripPassageStop1, tripPassageStop2);
    }

    @Test
    public void TripPassageStop_equal_should_not_be_true() {
        final TripPassageStop tripPassageStop1 = createSample(1).build();
        TripPassageStop tripPassageStop2 = createSample(2).build();
        assertNotEquals(tripPassageStop1, null);
        assertNotEquals(tripPassageStop1, new Object());
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setStopSeqNum(10)
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setStatus(-10)
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setId("unknown_id")
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setName("other_name")
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setShortName("other_short_name")
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setPlannedTime(PLANNED_TIME.minusMillis(1000))
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
        tripPassageStop2 = createSample(1)
                .setActualTime(ACTUAL_TIME.minusMillis(1000))
                .build();
        assertNotEquals(tripPassageStop1, tripPassageStop2);
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        final TripPassageStop tripPassageStop = adapter.fromJson("{\"unknown_tag\":null,\"stop\":{\"unknown_tag2\":null}}");
        assertNotNull(tripPassageStop);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag2", JsonToken.NULL);
    }
}
