package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RouteTest {

    private final static TypeAdapter<Route> adapter = new Route.Converter(new Gson());

    @Test
    public void typeadapter_read_full_information() throws Exception {
        Route input = new Route.Builder()
                .setRouteType(Route.ROUTE_TYPE_BUS)
                .setDirections(Collections.emptyList())
                .setId("random_id")
                .setName("name")
                .setShortName("shortName")
                .setAuthority("authority")
                .build();
        Route output = adapter.fromJson(adapter.toJson(input));
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
    public void Route_hashCode_should_be_equal() {
        Route route1 = new Route.Builder().setRouteType(29).build();
        Route route2 = new Route.Builder().setRouteType(29).build();
        assertEquals(route1.hashCode(), route2.hashCode());
    }

    @Test
    public void Route_equals_should_be_true() {
        final List<String> directions = new ArrayList<>();
        directions.add("dir1");
        directions.add("dir2");
        final Route route1 = new Route.Builder()
                .setId("id1")
                .setAuthority("authority")
                .setShortName("shortName")
                .setName("name")
                .setDirections(directions)
                .setAlerts(Collections.emptyList())
                .build();
        final Route route2 = new Route.Builder()
                .setId("id1")
                .setAuthority("authority")
                .setShortName("shortName")
                .setName("name")
                .setDirections(directions)
                .setAlerts(Collections.emptyList())
                .build();
        assertEquals(route1, route2);
        assertEquals(route1, route1);
    }

    @Test
    public void ShortStationInfo_equals_should_be_false() {
        final List<String> directions = new ArrayList<>();
        directions.add("dir1");
        directions.add("dir2");
        final Route route1 = new Route.Builder()
                .setId("id1")
                .setAuthority("authority")
                .setShortName("shortName")
                .setName("name")
                .setDirections(directions)
                .setAlerts(Collections.emptyList())
                .build();
        assertNotEquals(route1, null);
        assertNotEquals(route1, new Object());
        for (int i = 0; i < 6; i++) {
            final Route.Builder builder = new Route.Builder()
                    .setId("id1")
                    .setAuthority("authority")
                    .setShortName("shortName")
                    .setName("name")
                    .setDirections(directions)
                    .setAlerts(Collections.emptyList());
            switch (i) {
                case 0:
                    builder.setId("id2");
                    break;
                case 1:
                    builder.setAuthority("id2");
                    break;
                case 2:
                    builder.setShortName("id2");
                    break;
                case 3:
                    builder.setName("id2");
                    break;
                case 4:
                    builder.setDirections(Collections.emptyList());
                    break;
                case 5:
                    builder.setAlerts(directions);
                    break;
            }
            final Route route2 = builder.build();
            assertNotEquals(route1, route2);
        }
    }

    @Test
    public void Route_hashCode_should_not_be_equal() {
        Route route1 = new Route.Builder().setRouteType(29).build();
        Route route2 = new Route.Builder().setRouteType(20).build();
        assertNotEquals(route1.hashCode(), route2.hashCode());
    }

    @Test
    public void typeaadapter_skip_unknown_tag() throws Exception {
        assertNotNull(adapter.fromJson("{\"unknown_tag\":2}"));
    }

}
