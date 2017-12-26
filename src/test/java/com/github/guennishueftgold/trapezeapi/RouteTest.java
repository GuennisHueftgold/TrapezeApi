package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import java.util.Collections;

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
    public void typeaadapter_skip_unknown_tag() throws Exception {
        assertNotNull(adapter.fromJson("{\"unknown_tag\":2}"));
    }

}
