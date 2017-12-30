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


public final class VehiclePath {
    private final String mColor;
    private final List<VehiclePathPoint> mPathPoints;

    private VehiclePath(Builder builder) {
        this.mColor = builder.getColor();
        this.mPathPoints = Collections.unmodifiableList(builder.getPathPoints());
    }

    public String getColor() {
        return mColor;
    }

    public List<VehiclePathPoint> getPathPoints() {
        return mPathPoints;
    }

    @Override
    public String toString() {
        return "VehiclePath{" +
                "mColor='" + mColor + '\'' +
                ", mPathPoints=" + mPathPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclePath that = (VehiclePath) o;
        return Objects.equals(mColor, that.mColor) &&
                Objects.equals(mPathPoints, that.mPathPoints);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mColor, mPathPoints);
    }

    public static final class Builder {
        private String mColor;
        private List<VehiclePathPoint> mPathPoints = new ArrayList<>();

        public String getColor() {
            return mColor;
        }

        public Builder setColor(String color) {
            mColor = color;
            return this;
        }

        public List<VehiclePathPoint> getPathPoints() {
            return mPathPoints;
        }

        public Builder setPathPoints(List<VehiclePathPoint> pathPoints) {
            this.mPathPoints.clear();
            if (pathPoints != null)
                this.mPathPoints = pathPoints;
            return this;
        }

        public Builder addPathPoint(VehiclePathPoint vehiclePathPoint) {
            this.mPathPoints.add(vehiclePathPoint);
            return this;
        }

        public VehiclePath build() {
            return new VehiclePath(this);
        }
    }

    static final class Converter extends TypeAdapter<VehiclePath> {
        private final static String NAME_COLOR = "color", NAME_WAYPOINTS = "wayPoints";
        private final TypeAdapter<List<VehiclePathPoint>> mTypeAdapter;

        Converter(Gson gson) {
            this(gson.getAdapter(GeneralTypes.VEHICLE_PATH_POINT_LIST_TYPE_TOKEN));
        }

        Converter(TypeAdapter<List<VehiclePathPoint>> typeAdapter) {
            this.mTypeAdapter = typeAdapter;
        }

        @Override
        public void write(JsonWriter out, VehiclePath value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_COLOR)
                    .value(value.getColor());
            out.name(NAME_WAYPOINTS);
            this.mTypeAdapter.write(out, value.getPathPoints());
            out.endObject();
        }

        @Override
        public VehiclePath read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            in.beginObject();
            String name;
            Builder builder = new Builder();
            while (in.hasNext()) {
                name = in.nextName();
                switch (name) {
                    case NAME_COLOR:
                        builder.setColor(in.nextString());
                        break;
                    case NAME_WAYPOINTS:
                        builder.setPathPoints(this.mTypeAdapter.read(in));
                        break;
                    default:
                        Timber.d("Unkown name: %s", name);
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return builder.build();
        }
    }
}
