package com.github.guennishueftgold.trapezeapi;

import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SettingsTest {

    private final static Settings.Converter adapter = new Settings.Converter();

    static Settings.Builder createSample(int idx) {
        return new Settings.Builder()
                .setTimesliderEnabled(idx % 2 == 0)
                .setSuppressCountdownTimeIncrement(idx % 3 == 0)
                .setShowScheduleColumn(idx % 4 == 0)
                .setShowPassageTypeColumn(idx % 5 == 0)
                .setDefaultTimePreview((idx % 40) + 2)
                .setGeolocationEnabled(idx % 6 == 0)
                .setInitialLatitude(-idx * 20)
                .setInitialLongitude(idx * 20)
                .setInitialZoom(idx % 20)
                .setMapEnabled(idx % 7 == 0)
                .setMapShowControls(idx % 8 == 0)
                .setMapShowPatterns(idx % 9 == 0)
                .setMapShowStops(idx % 10 == 0)
                .setMapShowVehicles(idx % 11 == 0)
                .setMaxZoom(idx % 20)
                .setMinZoom(idx % 14)
                .setMobileEnabled(idx % 12 == 0)
                .setSearchByRoutesEnabled(idx % 13 == 0)
                .setSearchByStoppointsEnabled(idx % 14 == 0)
                .setShowAboutDepartureText(idx % 15 == 0)
                .setShowActualColumn(idx % 16 == 0)
                .setShowDepartingText(idx % 17 == 0)
                .setShowDepArrText(idx % 18 == 0)
                .setShowMixedColumn(idx % 19 == 0);
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        for (int i = 0; i < 40; i++) {
            final Settings input = createSample(i).build();
            final Settings output = adapter.fromJson(adapter.toJson(input));
            assertEquals(input, output);
        }
    }

    @Test
    public void typeadapter_check_read_null() throws Exception {
        assertEquals(adapter.fromJson("null"), null);
    }

    @Test
    public void typeadapter_check_write_null() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        final Settings settings = adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(settings);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }

    @Test
    public void Settings_equals_should_be_true() {
        final Settings settings1 = createSample(0)
                .build();
        final Settings settings2 = createSample(0)
                .build();
        assertEquals(settings1, settings1);
        assertEquals(settings1, settings2);
    }

    @Test
    public void Settings_equals_should_be_false() {
        final Settings settings1 = createSample(0)
                .build();
        assertNotEquals(settings1, null);
        assertNotEquals(settings1, new Object());
        for (int i = 1; i < 50; i++) {
            final Settings settings2 = createSample(i)
                    .build();
            assertNotEquals(settings1, settings2);
        }
    }

    @Test
    public void Settings_hashCode_should_be_equal() {
        final Settings settings1 = createSample(10).build();
        final Settings settings2 = createSample(10).build();
        assertEquals(settings1.hashCode(), settings2.hashCode());
    }

    @Test
    public void Settings_hashCode_should_not_be_equal() {
        final Settings settings1 = createSample(10).build();
        final Settings settings2 = createSample(9)
                .build();
        assertNotEquals(settings1.hashCode(), settings2.hashCode());
    }

}
