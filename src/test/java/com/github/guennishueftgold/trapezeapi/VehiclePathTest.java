package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    }
}
