package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class StationTest {

    private final static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
            .create();
    private final static TypeAdapter<Station> adapter = new Station.Converter(gson);

    @Test
    public void typeadapter_read_full_information() throws Exception {
        Station input = new Station.Builder()
                .setStopName("stop_name")
                .setStopShortName("stop_short_name")
                .setActual(Collections.emptyList())
                .setOld(Collections.emptyList())
                .setGeneralAlerts(Collections.emptyList())
                .setRoutes(Collections.emptyList())
                .setDirections(Collections.emptyList())
                .build();
        Station output = adapter.fromJson(adapter.toJson(input));
        assertTrue(output.equals(input));
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
    public void PathSegment_equals_should_be_true() {
        final Station station1 = new Station.Builder()
                .setStopName("stop_name")
                .setStopShortName("stop_short_name")
                .setActual(Collections.emptyList())
                .setOld(Collections.emptyList())
                .setGeneralAlerts(Collections.emptyList())
                .setRoutes(Collections.emptyList())
                .setDirections(Collections.emptyList())
                .build();
        final Station station2 = new Station.Builder()
                .setStopName("stop_name")
                .setStopShortName("stop_short_name")
                .setActual(Collections.emptyList())
                .setOld(Collections.emptyList())
                .setGeneralAlerts(Collections.emptyList())
                .setRoutes(Collections.emptyList())
                .setDirections(Collections.emptyList())
                .build();
        assertEquals(station1, station2);
    }

    @Test
    public void PathSegment_equals_should_be_false() {
        final Station station1 = new Station.Builder()
                .setStopName("stop_name")
                .setStopShortName("stop_short_name")
                .setActual(Collections.emptyList())
                .setOld(Collections.emptyList())
                .setGeneralAlerts(Collections.emptyList())
                .setRoutes(Collections.emptyList())
                .setDirections(Collections.emptyList())
                .build();
        final Station station2 = new Station.Builder()
                .setStopName("ooother_stop_name")
                .setStopShortName("stop_short_name")
                .setActual(Collections.emptyList())
                .setOld(Collections.emptyList())
                .setGeneralAlerts(Collections.emptyList())
                .setRoutes(Collections.emptyList())
                .setDirections(Collections.emptyList())
                .build();
        assertNotEquals(station1, station2);
        assertNotEquals(station1, null);
        assertNotEquals(station1, new Object());
    }

}
