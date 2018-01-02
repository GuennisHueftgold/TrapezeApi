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
                .setGeolocationEnabled(idx % 6 == 0)
                .setMapEnabled(idx % 7 == 0)
                .setMapShowControls(idx % 8 == 0)
                .setMapShowPatterns(idx % 9 == 0)
                .setMapShowStops(idx % 10 == 0)
                .setMapShowVehicles(idx % 11 == 0)
                .setMobileEnabled(idx % 12 == 0)
                .setSearchByRoutesEnabled(idx % 13 == 0)
                .setSearchByStoppointsEnabled(idx % 14 == 0)
                .setShowAboutDepartureText(idx % 15 == 0)
                .setShowActualColumn(idx % 16 == 0)
                .setShowDepartingText(idx % 17 == 0)
                .setShowDepArrText(idx % 18 == 0)
                .setShowMixedColumn(idx % 19 == 0)
                .setInitialLatitude(-(idx / 19))
                .setInitialLongitude(idx / 19)
                .setMaxZoom((idx / 19) + 5)
                .setMinZoom((idx / 19))
                .setInitialZoom((idx / 19))
                .setDefaultTimePreview((idx / 19) + 5);
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        for (int i = 0; i < 80; i++) {
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
        Settings.Builder settings1 = createSample(0);
        assertNotEquals(settings1
                .build(), null);
        assertNotEquals(settings1
                .build(), new Object());
        for (int i = 0; i < 24; i++) {
            settings1 = new Settings.Builder();
            Settings.Builder settings2 = new Settings.Builder();
            switch (i) {
                case 0:
                    settings1.setTimesliderEnabled(true);
                    settings2.setTimesliderEnabled(false);
                    break;
                case 1:
                    settings1.setSuppressCountdownTimeIncrement(true);
                    settings2.setSuppressCountdownTimeIncrement(false);
                    break;
                case 2:
                    settings1.setShowScheduleColumn(true);
                    settings2.setShowScheduleColumn(false);
                    break;
                case 3:
                    settings1.setShowPassageTypeColumn(true);
                    settings2.setShowPassageTypeColumn(false);
                    break;
                case 4:
                    settings1.setGeolocationEnabled(true);
                    settings2.setGeolocationEnabled(false);
                    break;
                case 5:
                    settings1.setMapEnabled(true);
                    settings2.setMapEnabled(false);
                    break;
                case 6:
                    settings1.setMapShowControls(true);
                    settings2.setMapShowControls(false);
                    break;
                case 7:
                    settings1.setMapShowPatterns(true);
                    settings2.setMapShowPatterns(false);
                    break;
                case 8:
                    settings1.setMapShowStops(true);
                    settings2.setMapShowStops(false);
                    break;
                case 9:
                    settings1.setSearchByRoutesEnabled(true);
                    settings2.setSearchByRoutesEnabled(false);
                    break;
                case 10:
                    settings1.setSearchByStoppointsEnabled(true);
                    settings2.setSearchByStoppointsEnabled(false);
                    break;
                case 11:
                    settings1.setShowAboutDepartureText(true);
                    settings2.setShowAboutDepartureText(false);
                    break;
                case 12:
                    settings1.setShowActualColumn(true);
                    settings2.setShowActualColumn(false);
                    break;
                case 13:
                    settings1.setShowDepartingText(true);
                    settings2.setShowDepartingText(false);
                    break;
                case 14:
                    settings1.setShowDepArrText(true);
                    settings2.setShowDepArrText(false);
                    break;
                case 15:
                    settings1.setShowMixedColumn(true);
                    settings2.setShowMixedColumn(false);
                    break;
                case 16:
                    settings1.setInitialLatitude(1);
                    settings2.setInitialLatitude(2);
                    break;
                case 17:
                    settings1.setInitialLongitude(1);
                    settings2.setInitialLongitude(2);
                    break;
                case 18:
                    settings1.setMaxZoom(1);
                    settings2.setMaxZoom(2);
                    break;
                case 19:
                    settings1.setMinZoom(1);
                    settings2.setMinZoom(2);
                    break;
                case 20:
                    settings1.setInitialZoom(1);
                    settings2.setInitialZoom(2);
                    break;
                case 21:
                    settings1.setDefaultTimePreview(1);
                    settings2.setDefaultTimePreview(2);
                    break;
                case 22:
                    settings1.setMapShowVehicles(true);
                    settings2.setMapShowVehicles(false);
                    break;
                case 23:
                    settings1.setMobileEnabled(true);
                    settings2.setMobileEnabled(false);
                    break;
            }
            assertNotEquals(settings1.build(), settings2.build());
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
