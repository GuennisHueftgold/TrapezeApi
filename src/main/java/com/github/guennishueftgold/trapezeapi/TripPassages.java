package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class TripPassages {

    private final List<TripPassageStop> mActual;
    private final String mDirectionText;
    private final List<TripPassageStop> mOld;
    private final String mRouteName;

    private TripPassages(TripPassages.Builder builder) {
        this.mActual = Collections.unmodifiableList(builder.getActual());
        this.mDirectionText = builder.getDirectionText();
        this.mOld = Collections.unmodifiableList(builder.getOld());
        this.mRouteName = builder.getRouteName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripPassages that = (TripPassages) o;
        return Objects.equals(mActual, that.mActual) &&
                Objects.equals(mDirectionText, that.mDirectionText) &&
                Objects.equals(mOld, that.mOld) &&
                Objects.equals(mRouteName, that.mRouteName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mActual, mDirectionText, mOld, mRouteName);
    }

    public List<TripPassageStop> getActual() {
        return mActual;
    }

    public String getDirectionText() {
        return mDirectionText;
    }

    public List<TripPassageStop> getOld() {
        return mOld;
    }

    public String getRouteName() {
        return mRouteName;
    }

    @Override
    public String toString() {
        return "TripPassages{" +
                "mActual=" + mActual +
                ", mDirectionText='" + mDirectionText + '\'' +
                ", mOld=" + mOld +
                ", mRouteName='" + mRouteName + '\'' +
                '}';
    }

    public static class Builder {
        private List<TripPassageStop> mActual = new ArrayList<>();
        private String mDirectionText;
        private List<TripPassageStop> mOld = new ArrayList<>();
        private String mRouteName;

        public List<TripPassageStop> getActual() {
            return mActual;
        }

        public Builder setActual(List<TripPassageStop> actual) {
            this.mActual.clear();
            if (actual != null)
                this.mActual.addAll(actual);
            return this;
        }

        public Builder addActual(TripPassageStop actual) {
            this.mActual.add(actual);
            return this;
        }

        public String getDirectionText() {
            return mDirectionText;
        }

        public Builder setDirectionText(String directionText) {
            mDirectionText = directionText;
            return this;
        }

        public List<TripPassageStop> getOld() {
            return mOld;
        }

        public Builder setOld(List<TripPassageStop> old) {
            this.mOld.clear();
            if (old != null)
                this.mOld.addAll(old);
            return this;
        }

        public Builder addOld(TripPassageStop old) {
            this.mOld.add(old);
            return this;
        }

        public String getRouteName() {
            return mRouteName;
        }

        public Builder setRouteName(String routeName) {
            mRouteName = routeName;
            return this;
        }

        public TripPassages build() {
            return new TripPassages(this);
        }
    }

    public final static class Converter extends TypeAdapter<TripPassages> {

        private final static String
                ROUTE_NAME = "routeName",
                DIRECTION_TEXT = "directionText",
                OLD = "old",
                ACTUAL = "actual";
        private final TypeAdapter<List<TripPassageStop>> mTripPassageStopConverter;

        private final TypeToken<List<TripPassageStop>> PASSAGE_STOP_LIST_TYPE_TOKEN = new TypeToken<List<TripPassageStop>>() {
        };

        public Converter(Gson gson) {
            this.mTripPassageStopConverter = gson.getAdapter(PASSAGE_STOP_LIST_TYPE_TOKEN);
        }

        @Override
        public void write(JsonWriter out, TripPassages value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(DIRECTION_TEXT).value(value.mDirectionText);
            out.name(ROUTE_NAME).value(value.mRouteName);
            out.name(OLD);
            this.mTripPassageStopConverter.write(out, value.mOld);
            out.name(ACTUAL);
            this.mTripPassageStopConverter.write(out, value.mActual);
            out.endObject();
        }

        @Override
        public TripPassages read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                return null;
            }
            in.beginObject();
            TripPassages.Builder tripPassages = new TripPassages.Builder();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(ROUTE_NAME) && in.peek() == JsonToken.STRING) {
                    tripPassages.setRouteName(in.nextString());
                } else if (name.equals(ACTUAL) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    tripPassages.setActual(this.mTripPassageStopConverter.read(in));
                } else if (name.equals(OLD) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    tripPassages.setOld(this.mTripPassageStopConverter.read(in));
                } else if (name.equals(DIRECTION_TEXT) && in.peek() == JsonToken.STRING) {
                    tripPassages.setDirectionText(in.nextString());
                } else {
                    Logger.reportUnknownName(this, name, in.peek());
                    in.skipValue();
                }
            }
            in.endObject();
            return tripPassages.build();
        }


    }
}
