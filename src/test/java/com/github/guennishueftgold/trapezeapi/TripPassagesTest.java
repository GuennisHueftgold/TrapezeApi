package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TripPassagesTest {

    private final static LocalTime TEST_TIME_PLANED = LocalTime.fromMillisOfDay(1000L);
    private final static LocalTime TEST_TIME_ACTUAL = LocalTime.fromMillisOfDay(2000L);
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new TrapezeApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<TripPassages> adapter = new TripPassages.Converter(gson);

    static TripPassages.Builder createSample(int idx) {
        return createSample(idx, 5, 9);
    }

    static TripPassages.Builder createSample(int idx, int actual, int old) {
        final TripPassages.Builder builder = new TripPassages.Builder()
                .setDirectionText("direction_" + idx)
                .setRouteName("route_name_" + idx);
        for (int i = 0; i < actual; i++) {
            builder.addActual(createTripPassagesStop(i));
        }
        for (int i = 0; i < old; i++) {
            builder.addOld(createTripPassagesStop(-i));
        }
        return builder;
    }

    private static TripPassageStop createTripPassagesStop(int idx) {
        return new TripPassageStop.Builder()
                .setStatus(DepartureStatus.STATUS_PLANNED)
                .setStopSeqNum(idx)
                .setShortName("short_name_" + idx)
                .setId("id_" + idx)
                .setName("name_" + idx)
                .setActualTime(TEST_TIME_ACTUAL.plusMillis(idx))
                .setPlannedTime(TEST_TIME_PLANED.plusMillis(idx))
                .build();
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final TripPassages tripPassages = createSample(0).build();
        final TripPassages output = adapter.fromJson(adapter.toJson(tripPassages));
        assertEquals(tripPassages, output);
        assertNull(adapter.fromJson(adapter.toJson(null)));
    }

    @Test
    public void TripPassages_equals_should_be_true() {
        final TripPassages tripPassages1 = createSample(0)
                .build();
        final TripPassages tripPassages2 = createSample(0)
                .build();
        assertEquals(tripPassages1, tripPassages2);
        assertEquals(tripPassages1, tripPassages1);
    }

    @Test
    public void TripPassages_equals_should_be_false() {
        final TripPassages tripPassages1 = createSample(0)
                .build();
        final TripPassages tripPassages2 = createSample(1)
                .build();
        final TripPassages tripPassages3 = createSample(2).build();
        assertNotEquals(tripPassages1, tripPassages3);
        assertNotEquals(tripPassages2, tripPassages3);
        assertNotEquals(tripPassages1, null);
        assertNotEquals(tripPassages1, new Object());
    }

    @Test
    public void TripPassages_hashCode_should_be_equal() {
        final TripPassages tripPassages1 = createSample(10).build();
        final TripPassages tripPassages2 = createSample(10).build();
        assertEquals(tripPassages1.hashCode(), tripPassages2.hashCode());
    }

    @Test
    public void TripPassages_hashCode_should_not_be_equal() {
        final TripPassages tripPassages1 = createSample(10).build();
        final TripPassages tripPassages2 = createSample(9)
                .build();
        assertNotEquals(tripPassages1.hashCode(), tripPassages2.hashCode());
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        TripPassages tripPassages = this.adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(tripPassages);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void TripPassages_getOld_should_be_immutable() {
        final TripPassages tripPassages = createSample(0).build();
        tripPassages.getActual().remove(0);
    }

    @Test
    public void Builder_setActual_null_should_clear_list() {
        final TripPassages.Builder builder = createSample(0, 2, 3);
        assertEquals(builder.getActual().size(), 2);
        builder.setActual(null);
        assertEquals(builder.getActual().size(), 0);
    }

    @Test
    public void Builder_setOld_null_should_clear_list() {
        final TripPassages.Builder builder = createSample(0, 2, 3);
        assertEquals(builder.getOld().size(), 3);
        builder.setOld(null);
        assertEquals(builder.getOld().size(), 0);
    }
}
