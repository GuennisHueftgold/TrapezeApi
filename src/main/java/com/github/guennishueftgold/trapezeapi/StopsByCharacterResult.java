package com.github.guennishueftgold.trapezeapi;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class StopsByCharacterResult {
    private final List<ShortStationInfo> mResults;

    private StopsByCharacterResult(Builder builder) {
        this.mResults = builder.mResults;
    }

    public List<ShortStationInfo> getResults() {
        return mResults;
    }

    @Override
    public String toString() {
        return "StopsByCharacterResult{" +
                "mResults=" + mResults +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopsByCharacterResult that = (StopsByCharacterResult) o;
        return Objects.equals(mResults, that.mResults);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mResults);
    }

    public static class Builder {
        private List<ShortStationInfo> mResults;

        public List<ShortStationInfo> getResults() {
            return mResults;
        }

        public Builder setResults(List<ShortStationInfo> results) {
            mResults = results;
            return this;
        }

        public StopsByCharacterResult build() {
            return new StopsByCharacterResult(this);
        }
    }

    public static class Converter extends TypeAdapter<StopsByCharacterResult> {


        private final static String NAME_STOPS = "stops";
        private final TypeAdapter<List<ShortStationInfo>> mListConverter;

        public Converter(Gson gson) {
            this(gson.getAdapter(GeneralTypes.SHORT_STATION_INFO_LIST_TYPE_TOKEN));
        }

        public Converter(TypeAdapter<List<ShortStationInfo>> listConverter) {
            this.mListConverter = listConverter;
        }

        @Override
        public void write(JsonWriter out, StopsByCharacterResult value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_STOPS);
            this.mListConverter.write(out, value.getResults());
            out.endObject();
        }

        @Override
        public StopsByCharacterResult read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            in.beginObject();
            String name;
            Builder builder = new Builder();
            while (in.hasNext()) {
                name = in.nextName();
                if (NAME_STOPS.equalsIgnoreCase(name)
                        && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setResults(this.mListConverter.read(in));
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
            return builder.build();
        }
    }
}
