package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehiclePathTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<VehiclePath> adapter = new VehiclePath.Converter(gson);

    static VehiclePath.Builder createSample() {
        return new VehiclePath.Builder()
                .setColor("#FF00FF")
                .addPathPoint(createSamplePathPoint(0))
                .addPathPoint(createSamplePathPoint(1));
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
        assertNotEquals(path1, path2);
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
}
