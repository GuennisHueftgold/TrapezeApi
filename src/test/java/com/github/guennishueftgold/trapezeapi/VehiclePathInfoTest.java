package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VehiclePathInfoTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new TrapezeApiTypeAdapterFactory())
            .create();
    private final TypeAdapter<VehiclePathInfo> adapter = new VehiclePathInfo.Converter(gson);

    private static final VehiclePathInfo.Builder createSample(int paths) {
        final VehiclePathInfo.Builder builder = new VehiclePathInfo.Builder();
        for (int i = 0; i < paths; i++) {
            builder.addVehiclePath(VehiclePathTest.createSample().build());
        }
        return builder;
    }

    @Test
    public void typeadapter_read_full_information() throws Exception {
        final VehiclePathInfo input = createSample(10).build();
        final VehiclePathInfo output = adapter.fromJson(adapter.toJson(input));
        assertEquals(input, output);
    }

    @Test
    public void TypeAdapter_skip_unknown_name() throws IOException {
        Logger logger = mock(Logger.class);
        Logger.setInstance(logger);
        VehiclePathInfo vehiclePathInfo = this.adapter.fromJson("{\"unknown_tag\":null}");
        assertNotNull(vehiclePathInfo);
        verify(logger, times(1)).unknownName(adapter, "unknown_tag", JsonToken.NULL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void VehiclePathInfo_getVehiclePaths_should_be_immutable() {
        final VehiclePathInfo vehiclePathInfo = createSample(2).build();
        vehiclePathInfo.getVehiclePaths().remove(0);
    }

    @Test
    public void TypeAdapter_read_null_should_work() throws IOException {
        assertEquals(adapter.fromJson("null"), null);
    }

    @Test
    public void TypeAdapter_write_null_should_work() {
        assertEquals(adapter.toJson(null), "null");
    }

    @Test
    public void VehiclePathInfo_equals_should_be_true() {
        final VehiclePathInfo vehiclePathInfo1 = createSample(4).build();
        final VehiclePathInfo vehiclePathInfo2 = createSample(4).build();
        assertEquals(vehiclePathInfo1, vehiclePathInfo2);
        assertEquals(vehiclePathInfo1, vehiclePathInfo1);
    }

    @Test
    public void VehiclePath_equals_should_be_false() {
        final VehiclePathInfo vehiclePathInfo1 = createSample(4).build();
        final VehiclePathInfo vehiclePathInfo2 = createSample(3).build();
        assertNotEquals(vehiclePathInfo1, vehiclePathInfo2);
        assertNotEquals(vehiclePathInfo1, null);
        assertNotEquals(vehiclePathInfo1, new Object());
    }

    @Test
    public void VehiclePath_hashCode_should_be_equal() {
        final VehiclePathInfo vehiclePathInfo1 = createSample(4).build();
        final VehiclePathInfo vehiclePathInfo2 = createSample(4).build();
        assertEquals(vehiclePathInfo1.hashCode(), vehiclePathInfo2.hashCode());
    }

    @Test
    public void VehiclePath_hashCode_should_not_be_equal() {
        final VehiclePathInfo vehiclePathInfo1 = createSample(5).build();
        final VehiclePathInfo vehiclePathInfo2 = createSample(4).build();
        assertNotEquals(vehiclePathInfo1.hashCode(), vehiclePathInfo2.hashCode());
    }

}
