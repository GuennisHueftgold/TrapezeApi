package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartureTest {

    private final static TypeAdapter<LocalTime> localTimeAdapter=new LocalTimeTypeAdapter();
    private final static TypeAdapter<Departure> adapter = new Departure.Converter(localTimeAdapter);

    @Test
    public void typeadapter_read_full_information() throws Exception {
        Departure input = new Departure.Builder()
                .build();
        Departure output = adapter.fromJson(adapter.toJson(input));
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
    public void Departure_equals_should_be_true() throws Exception{
        final Departure departure1=new Departure.Builder().setStatus(29).build();
        final Departure departure2=new Departure.Builder().setStatus(29).build();
        assertEquals(departure1,departure2);
    }

    @Test
    public void Departure_equals_should_be_false() throws Exception{
        final Departure departure1=new Departure.Builder().setStatus(29).build();
        final Departure departure2=new Departure.Builder().setStatus(2).build();
        assertNotEquals(departure1,departure2);
        assertNotEquals(departure1,null);
        assertNotEquals(departure1,new Object());
    }

}
