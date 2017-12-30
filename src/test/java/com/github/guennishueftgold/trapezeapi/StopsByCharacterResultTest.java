package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.*;

public class StopsByCharacterResultTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<StopsByCharacterResult> adapter = new StopsByCharacterResult.Converter(gson);

    private static StopsByCharacterResult.Builder createSample() {
        return new StopsByCharacterResult.Builder()
                .addResult(createShortStationInfo(0))
                .addResult(createShortStationInfo(1))
                .addResult(createShortStationInfo(2));
    }

    public static ShortStationInfo createShortStationInfo(int index) {
        return new ShortStationInfo.Builder()
                .setStopShortName("short_name_" + index)
                .setStopName("stop_name_" + index)
                .setId("id_" + index).build();
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
    public void typeadapter_read_full_information() throws Exception {
        StopsByCharacterResult input = createSample().build();
        StopsByCharacterResult output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
    }

    @Test
    public void Departure_equals_should_be_true() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample().build();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample().build();
        assertEquals(stopsByCharacterResult1, stopsByCharacterResult2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Departure_list_should_be_unmutable() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample().build();
        stopsByCharacterResult1.getResults().remove(0);
    }

    @Test
    public void Departure_equals_should_be_false() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample().build();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample()
                .addResult(createShortStationInfo(3))
                .build();
        assertNotEquals(stopsByCharacterResult1, stopsByCharacterResult2);
        assertNotEquals(stopsByCharacterResult1, null);
        assertNotEquals(stopsByCharacterResult1, new Object());
    }

    @Test
    public void Departure_hashCode_should_be_equal() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample().build();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample().build();
        assertEquals(stopsByCharacterResult1.hashCode(), stopsByCharacterResult2.hashCode());
    }

    @Test
    public void Departure_hashCode_should_not_be_equal() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample().build();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample()
                .addResult(createShortStationInfo(3))
                .build();
        assertNotEquals(stopsByCharacterResult1.hashCode(), stopsByCharacterResult2.hashCode());
    }

}
