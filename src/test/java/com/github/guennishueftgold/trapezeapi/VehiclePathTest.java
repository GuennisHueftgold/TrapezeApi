package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VehiclePathTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<VehiclePath> adapter = new VehiclePath.Converter(gson);

    static VehiclePath.Builder createSample() {
        return createSample(5);
    }

    static VehiclePath.Builder createSample(int pathPoints) {
        final VehiclePath.Builder builder = new VehiclePath.Builder()
                .setColor("#FF00FF");
        for (int i = 0; i < pathPoints; i++) {
            builder.addPathPoint(createSamplePathPoint(i));
        }
        return builder;
    }

    private static VehiclePathPoint createSamplePathPoint(int idx) {
        return new VehiclePathPoint.Builder()
                .setLatitude(idx)
                .setLongitude(idx * 2)
                .setSequence(idx)
                .build();
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final VehiclePath vehiclePath = createSample().build();
        final VehiclePath output = adapter.fromJson(adapter.toJson(vehiclePath));
        assertEquals(vehiclePath, output);
        assertNull(adapter.fromJson(adapter.toJson(null)));
    }

    @Test
    public void VehiclePath_equals_should_be_true() {
        final VehiclePath path1 = createSample()
                .addPathPoint(createSamplePathPoint(4))
                .build();
        final VehiclePath path2 = createSample()
                .addPathPoint(createSamplePathPoint(4))
                .build();
        assertEquals(path1, path2);
        assertEquals(path1, path1);
    }

    @Test
    public void VehiclePath_equals_should_be_false() {
        final VehiclePath path1 = createSample()
                .setColor("#00FF00")
                .build();
        final VehiclePath path2 = createSample()
                .addPathPoint(createSamplePathPoint(4))
                .build();
        final VehiclePath path3 = createSample().build();
        assertNotEquals(path1, path3);
        assertNotEquals(path2, path3);
        assertNotEquals(path1, null);
        assertNotEquals(path1, new Object());
    }

    @Test
    public void VehiclePath_hashCode_should_be_equal() {
        final VehiclePath path1 = createSample().build();
        final VehiclePath path2 = createSample().build();
        assertEquals(path1.hashCode(), path2.hashCode());
    }

    @Test
    public void VehiclePath_hashCode_should_not_be_equal() {
        final VehiclePath path1 = createSample().build();
        final VehiclePath path2 = createSample()
                .addPathPoint(createSamplePathPoint(4))
                .build();
        assertNotEquals(path1.hashCode(), path2.hashCode());
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        VehiclePath path = this.adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(path);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }
}
