package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StopsByCharacterResultTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<StopsByCharacterResult> adapter = new StopsByCharacterResult.Converter(gson);

    private static StopsByCharacterResult createSample() {
        List<ShortStationInfo> shortStationInfoList = new ArrayList<>();
        shortStationInfoList.add(new ShortStationInfo.Builder()
                .setId("id1")
                .setStopName("stop_name_1")
                .setStopShortName("short_name_1").build());
        shortStationInfoList.add(new ShortStationInfo.Builder()
                .setId("id2")
                .setStopName("stop_name_2")
                .setStopShortName("short_name_2").build());
        return new StopsByCharacterResult.Builder()
                .setResults(shortStationInfoList)
                .build();
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        StopsByCharacterResult input = createSample();
        StopsByCharacterResult output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
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
    public void Departure_equals_should_be_true() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample();
        assertEquals(stopsByCharacterResult1, stopsByCharacterResult2);
    }

    @Test
    public void Departure_equals_should_be_false() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample();
        stopsByCharacterResult2.getResults().remove(0);
        assertNotEquals(stopsByCharacterResult1, stopsByCharacterResult2);
        assertNotEquals(stopsByCharacterResult1, null);
        assertNotEquals(stopsByCharacterResult1, new Object());
    }

    @Test
    public void Departure_hashCode_should_be_equal() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample();
        assertEquals(stopsByCharacterResult1.hashCode(), stopsByCharacterResult2.hashCode());
    }

    @Test
    public void Departure_hashCode_should_not_be_equal() {
        final StopsByCharacterResult stopsByCharacterResult1 = createSample();
        final StopsByCharacterResult stopsByCharacterResult2 = createSample();
        stopsByCharacterResult2.getResults().remove(0);
        assertNotEquals(stopsByCharacterResult1.hashCode(), stopsByCharacterResult2.hashCode());
    }

}
