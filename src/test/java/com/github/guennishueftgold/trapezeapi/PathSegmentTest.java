package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathSegmentTest {

    private final static TypeAdapter<PathSegment> adapter = new PathSegment.Converter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        PathSegment input = new PathSegment.Builder()
                .build();
        PathSegment output = adapter.fromJson(adapter.toJson(input));
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
        final PathSegment pathSegment1 = new PathSegment.Builder().setAngle(29).build();
        final PathSegment pathSegment2 = new PathSegment.Builder().setAngle(29).build();
        assertEquals(pathSegment1, pathSegment2);
    }

    @Test
    public void PathSegment_equals_should_be_false() {
        final PathSegment pathSegment1 = new PathSegment.Builder().setAngle(29).build();
        final PathSegment pathSegment2 = new PathSegment.Builder().setAngle(2).build();
        assertNotEquals(pathSegment1, pathSegment2);
        assertNotEquals(pathSegment1, null);
        assertNotEquals(pathSegment1, new Object());
    }

}
