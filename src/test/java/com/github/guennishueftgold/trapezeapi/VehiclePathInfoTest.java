package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class VehiclePathInfoTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
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
}
