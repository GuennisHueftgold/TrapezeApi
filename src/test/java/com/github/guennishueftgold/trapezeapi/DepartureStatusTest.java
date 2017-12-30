package com.github.guennishueftgold.trapezeapi;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        final int departureStatus = adapter.fromJson("\"unknown_value\"");
        assertEquals(departureStatus, DepartureStatus.STATUS_UNKNOWN);
        verify(logger, times(1)).unknownValue(adapter, "unknown_value");
    }

}
