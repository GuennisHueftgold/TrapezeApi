package com.github.guennishueftgold.trapezeapi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DepartureStatusTest {

    private final static DepartureStatus.Converter adapter = new DepartureStatus.Converter();

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final int[] testValues = {DepartureStatus.STATUS_DEPARTED,
                DepartureStatus.STATUS_PLANNED,
                DepartureStatus.STATUS_PREDICTED,
                DepartureStatus.STATUS_STOPPING,
                DepartureStatus.STATUS_UNKNOWN};
        for (int testValue : testValues) {
            int output = adapter.fromJson(adapter.toJson(testValue));
            assertEquals(testValue, output);
        }
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertEquals((int) adapter.fromJson("null"), DepartureStatus.STATUS_UNKNOWN);
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test(expected = IllegalStateException.class)
    public void typeaadapter_skip_unknown_tag() throws Exception {
        assertNotNull(adapter.fromJson("{\"unknown_tag\":2}"));
    }

}
