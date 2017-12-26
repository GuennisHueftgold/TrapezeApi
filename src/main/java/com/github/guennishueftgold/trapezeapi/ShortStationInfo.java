package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;


public final class ShortStationInfo {
    private final String mId;
    private final String mStopName;
    private final String mStopShortName;

    private ShortStationInfo(Builder builder) {
        this.mId = builder.mId;
        this.mStopName = builder.mStopName;
        this.mStopShortName = builder.mStopShortName;
    }

    public String getId() {
        return mId;
    }

    public String getStopName() {
        return mStopName;
    }

    public String getStopShortName() {
        return mStopShortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortStationInfo that = (ShortStationInfo) o;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(mStopName, that.mStopName) &&
                Objects.equals(mStopShortName, that.mStopShortName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mStopName, mStopShortName);
    }

    public static class Builder {
        private String mId;
        private String mStopName;
        private String mStopShortName;

        public String getId() {
            return mId;
        }

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public String getStopName() {
            return mStopName;
        }

        public Builder setStopName(String stopName) {
            mStopName = stopName;
            return this;
        }

        public String getStopShortName() {
            return mStopShortName;
        }

        public Builder setStopShortName(String stopShortName) {
            mStopShortName = stopShortName;
            return this;
        }

        public ShortStationInfo build() {
            return new ShortStationInfo(this);
        }
    }

    public static class Converter extends TypeAdapter<ShortStationInfo> {
        private final static String NAME_ID = "id", NAME_NAME = "name", NAME_NUMBER = "number";

        @Override
        public void write(JsonWriter out, ShortStationInfo value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_NAME)
                    .value(value.getStopName());
            out.name(NAME_ID)
                    .value(value.getId());
            out.name(NAME_NUMBER)
                    .value(value.getStopShortName());
            out.endObject();
        }

        @Override
        public ShortStationInfo read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            in.beginObject();
            String name;
            Builder builder = new Builder();
            while (in.hasNext()) {
                name = in.nextName();
                if (NAME_NAME.equalsIgnoreCase(name) && in.peek() == JsonToken.STRING) {
                    builder.setStopName(in.nextString());
                } else if (NAME_ID.equalsIgnoreCase(name) && in.peek() == JsonToken.STRING) {
                    builder.setId(in.nextString());
                } else if (NAME_NUMBER.equalsIgnoreCase(name) && in.peek() == JsonToken.STRING) {
                    builder.setStopShortName(in.nextString());
                } else {
                    Timber.d("Unknown Name: %s", name);
                    in.skipValue();
                }
            }
            in.endObject();
            return builder.build();
        }
    }
}
