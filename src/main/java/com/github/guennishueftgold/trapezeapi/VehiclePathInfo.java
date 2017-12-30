package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public final class VehiclePathInfo {
    private final List<VehiclePath> mVehiclePaths;

    private VehiclePathInfo(Builder builder) {
        this.mVehiclePaths = Collections.unmodifiableList(builder.getVehiclePaths());
    }

    public List<VehiclePath> getVehiclePaths() {
        return mVehiclePaths;
    }

    @Override
    public String toString() {
        return "VehiclePathInfo{" +
                "mVehiclePaths=" + mVehiclePaths +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclePathInfo that = (VehiclePathInfo) o;
        return Objects.equals(mVehiclePaths, that.mVehiclePaths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mVehiclePaths);
    }

    public static final class Builder {

        private List<VehiclePath> mVehiclePaths = new ArrayList<>();

        public List<VehiclePath> getVehiclePaths() {
            return mVehiclePaths;
        }

        public Builder setVehiclePaths(List<VehiclePath> vehiclePaths) {
            this.mVehiclePaths.clear();
            if (vehiclePaths != null)
                this.mVehiclePaths.addAll(vehiclePaths);
            return this;
        }

        public VehiclePathInfo build() {
            return new VehiclePathInfo(this);
        }

        public Builder addVehiclePath(VehiclePath vehiclePath) {
            this.mVehiclePaths.add(vehiclePath);
            return this;
        }
    }

    static final class Converter extends TypeAdapter<VehiclePathInfo> {
        private final static String NAME_PATHS = "paths";
        private final TypeAdapter<List<VehiclePath>> mTypeAdapter;

        public Converter(Gson gson) {
            this.mTypeAdapter = gson.getAdapter(GeneralTypes.VEHICLE_PATH_LIST_TYPE_TOKEN);
        }

        @Override
        public void write(JsonWriter out, VehiclePathInfo value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_PATHS);
            this.mTypeAdapter.write(out, value.getVehiclePaths());
            out.endObject();
        }

        @Override
        public VehiclePathInfo read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            Builder builder = new Builder();
            in.beginObject();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                switch (name) {
                    case NAME_PATHS:
                        builder.setVehiclePaths(this.mTypeAdapter.read(in));
                        break;
                    default:
                        Logger.reportUnknownName(this, name, in.peek());
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return builder.build();
        }
    }
}
