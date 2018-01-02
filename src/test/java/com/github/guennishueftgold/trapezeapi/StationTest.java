package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.*;

public class StationTest {

    private final static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new TrapezeApiTypeAdapterFactory())
            .create();
    private final static TypeAdapter<Station> adapter = new Station.Converter(gson);

    public final static Station.Builder createSample(int idx) {
        return createSample(idx, 4, 5, 6, 7, 8);
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

    public final static Station.Builder createSample(int idx, int actual, int old, int alerts, int routes, int directions) {
        final Station.Builder builder = new Station.Builder()
                .setStopName("stop_name_" + idx)
                .setStopShortName("stop_short_name_" + idx);
        for (int i = 0; i < actual; i++)
            builder.addActual(DepartureTest.createSample(i).build());
        for (int i = 0; i < old; i++)
            builder.addOld(DepartureTest.createSample(i).build());
        for (int i = 0; i < alerts; i++)
            builder.addGeneralAlert("alert_" + i);
        for (int i = 0; i < routes; i++)
            builder.addRoute(RouteTest.createSample(i).build());
        for (int i = 0; i < directions; i++)
            builder.addDirection("direction_" + i);
        return builder;
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final Station input = createSample(1).build();
        Station output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
    }

    @Test
    public void PathSegment_equals_should_be_true() {
        final Station station1 = createSample(1).build();
        final Station station2 = createSample(1).build();
        assertEquals(station1, station2);
        assertEquals(station1, station1);
    }

    @Test
    public void PathSegment_equals_should_be_false() {
        final Station station1 = createSample(1).build();
        final Station station2 = createSample(2).build();
        assertNotEquals(station1, station2);
        assertNotEquals(station1, null);
        assertNotEquals(station1, new Object());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Station_getGeneralAlerts_should_be_immutable() {
        final Station station = createSample(2).build();
        station.getGeneralAlerts().remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Station_getDirections_should_be_immutable() {
        final Station station = createSample(2).build();
        station.getDirections().remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Station_getActual_should_be_immutable() {
        final Station station = createSample(2).build();
        station.getActual().remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Station_getOld_should_be_immutable() {
        final Station station = createSample(2).build();
        station.getOld().remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Station_getRoutes_should_be_immutable() {
        final Station station = createSample(2).build();
        station.getRoutes().remove(0);
    }

}
