package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DepartureTest {

    private final static TypeAdapter<LocalTime> localTimeAdapter = new LocalTimeTypeAdapter();
    private final static TypeAdapter<Departure> adapter = new Departure.Converter(localTimeAdapter);

    public final static Departure.Builder createSample(int idx) {
        final int[] testValues = {DepartureStatus.STATUS_DEPARTED,
                DepartureStatus.STATUS_PLANNED,
                DepartureStatus.STATUS_PREDICTED,
                DepartureStatus.STATUS_STOPPING,
                DepartureStatus.STATUS_UNKNOWN};
        final LocalTime localTime1 = LocalTime.fromMillisOfDay(1000);
        final LocalTime localTime2 = LocalTime.fromMillisOfDay(10000);
        return new Departure.Builder()
                .setStatus(testValues[idx % testValues.length])
                .setMixedTime("mixed_time_" + idx)
                .setDirection("direction_" + idx)
                .setPassageId("passage_id_" + idx)
                .setPatternText("pattern_text_" + idx)
                .setRouteId("route_" + idx)
                .setTripId("trip_" + idx)
                .setVehicleId("vehicle_" + idx)
                .setActualRelativeTime(idx)
                .setActualTime(localTime1.plusMillis(idx))
                .setPlannedTime(localTime2.plusMillis(idx));
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final Departure input = createSample(0).build();
        final Departure output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertNull(adapter.fromJson("null"));
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test
    public void typeaadapter_skip_unknown_tag() throws Exception {
        assertNotNull(adapter.fromJson("{\"unknown_tag\":2}"));
    }

    @Test
    public void Departure_equals_should_be_true() {
        final Departure departure1 = createSample(1).build();
        final Departure departure2 = createSample(1).build();
        assertEquals(departure1, departure2);
        assertEquals(departure1, departure1);
    }

    @Test
    public void Departure_equals_should_be_false() {
        final Departure departure1 = createSample(1).build();
        assertNotEquals(departure1, null);
        assertNotEquals(departure1, new Object());
        Departure departure2 = createSample(1)
                .setActualRelativeTime(-10)
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setActualTime(null)
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setRouteId("other_route_id")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setVehicleId("other_vehicle_id")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setTripId("other_trip_id")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setPatternText("other_pattern")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setPlannedTime(null)
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setPassageId("other_passage_id")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setDirection("other_direction")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setMixedTime("other_mixed_time")
                .build();
        assertNotEquals(departure1, departure2);
        departure2 = createSample(1)
                .setStatus(29)
                .build();
        assertNotEquals(departure1, departure2);
    }

    @Test
    public void Departure_hashCode_should_be_equal() {
        final Departure departure1 = createSample(1).build();
        final Departure departure2 = createSample(1).build();
        assertEquals(departure1.hashCode(), departure2.hashCode());
    }

    @Test
    public void Departure_hashCode_should_not_be_equal() {
        final Departure departure1 = createSample(1).build();
        final Departure departure2 = createSample(2).build();
        assertNotEquals(departure1.hashCode(), departure2.hashCode());
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        Departure departure = adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(departure);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }
}
