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

/**
 * Provides information about a Station
 */
public final class Station {
    private final String mStopName;
    private final String mStopShortName;
    private final List<String> mDirections;
    private final long mFirstPassageTime;
    private final List<String> mGeneralAlerts;
    private final long mLastPassageTime;
    private final List<Departure> mActual;
    private final List<Departure> mOld;
    private final List<Route> mRoutes;

    private Station(Builder builder) {
        this.mStopName = builder.getStopName();
        this.mStopShortName = builder.getStopShortName();
        this.mDirections = Collections.unmodifiableList(builder.getDirections());
        this.mFirstPassageTime = builder.getFirstPassageTime();
        this.mGeneralAlerts = Collections.unmodifiableList(builder.getGeneralAlerts());
        this.mLastPassageTime = builder.getLastPassageTime();
        this.mActual = Collections.unmodifiableList(builder.getActual());
        this.mOld = Collections.unmodifiableList(builder.getOld());
        this.mRoutes = Collections.unmodifiableList(builder.getRoutes());
    }

    /**
     * Gets the list of current routes serviced by the station
     *
     * @return a list of {@link Route}s
     */
    public List<Route> getRoutes() {
        return mRoutes;
    }

    /**
     * Gets the stop name of the station
     *
     * @return the stop name
     */
    public String getStopName() {
        return mStopName;
    }

    /**
     * Get the stations short name
     *
     * @return the short name
     */
    public String getStopShortName() {
        return mStopShortName;
    }

    public List<String> getDirections() {
        return mDirections;
    }

    public long getFirstPassageTime() {
        return mFirstPassageTime;
    }

    public List<String> getGeneralAlerts() {
        return mGeneralAlerts;
    }

    public long getLastPassageTime() {
        return mLastPassageTime;
    }

    public List<Departure> getActual() {
        return mActual;
    }

    public List<Departure> getOld() {
        return mOld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return mFirstPassageTime == station.mFirstPassageTime &&
                mLastPassageTime == station.mLastPassageTime &&
                Objects.equals(mStopName, station.mStopName) &&
                Objects.equals(mStopShortName, station.mStopShortName) &&
                Objects.equals(mDirections, station.mDirections) &&
                Objects.equals(mGeneralAlerts, station.mGeneralAlerts) &&
                Objects.equals(mActual, station.mActual) &&
                Objects.equals(mOld, station.mOld) &&
                Objects.equals(mRoutes, station.mRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mStopName, mStopShortName, mDirections, mFirstPassageTime, mGeneralAlerts, mLastPassageTime, mActual, mOld, mRoutes);
    }

    @Override
    public String toString() {
        return "Station{" +
                "stopName='" + mStopName + '\'' +
                ", stopShortName='" + mStopShortName + '\'' +
                ", directions=" + mDirections +
                ", firstPassageTime=" + mFirstPassageTime +
                ", generalAlerts=" + mGeneralAlerts +
                ", lastPassageTime=" + mLastPassageTime +
                ", actual=" + mActual +
                ", old=" + mOld +
                ", routes=" + mRoutes +
                '}';
    }

    public final static class Builder {

        private String mStopName;
        private String mStopShortName;
        private List<String> mDirections = new ArrayList<>();
        private long mFirstPassageTime;
        private List<String> mGeneralAlerts = new ArrayList<>();
        private long mLastPassageTime;
        private List<Departure> mActual = new ArrayList<>();
        private List<Departure> mOld = new ArrayList<>();
        private List<Route> mRoutes = new ArrayList<>();

        public String getStopName() {
            return mStopName;
        }

        public Builder setStopName(String stopName) {
            mStopName = stopName;
            return this;
        }

        public List<Route> getRoutes() {
            return mRoutes;
        }

        public Builder setRoutes(List<Route> routes) {
            this.mRoutes.clear();
            if (routes != null)
                this.mRoutes.addAll(routes);
            return this;
        }

        public String getStopShortName() {
            return mStopShortName;
        }

        public Builder setStopShortName(String stopShortName) {
            mStopShortName = stopShortName;
            return this;
        }

        public List<String> getDirections() {
            return mDirections;
        }

        public Builder setDirections(List<String> directions) {
            this.mDirections.clear();
            if (directions != null)
                this.mDirections.addAll(directions);
            return this;
        }

        public long getFirstPassageTime() {
            return mFirstPassageTime;
        }

        public Builder setFirstPassageTime(long firstPassageTime) {
            mFirstPassageTime = firstPassageTime;
            return this;
        }

        public List<String> getGeneralAlerts() {
            return mGeneralAlerts;
        }

        public Builder setGeneralAlerts(List<String> generalAlerts) {
            this.mGeneralAlerts.clear();
            if (generalAlerts != null)
                this.mGeneralAlerts.addAll(generalAlerts);
            return this;
        }

        public long getLastPassageTime() {
            return mLastPassageTime;
        }

        public Builder setLastPassageTime(long lastPassageTime) {
            mLastPassageTime = lastPassageTime;
            return this;
        }

        public List<Departure> getActual() {
            return mActual;
        }

        public Builder setActual(List<Departure> actual) {
            this.mActual.clear();
            if (actual != null)
                this.mActual.addAll(actual);
            return this;
        }

        public List<Departure> getOld() {
            return mOld;
        }

        public Builder setOld(List<Departure> old) {
            this.mOld.clear();
            if (old != null)
                this.mOld.addAll(old);
            return this;
        }

        public Station build() {
            return new Station(this);
        }

        public Builder addDirection(final String direction) {
            this.mDirections.add(direction);
            return this;
        }

        public Builder addActual(final Departure departure) {
            this.mActual.add(departure);
            return this;
        }

        public Builder addOld(final Departure departure) {
            this.mOld.add(departure);
            return this;
        }

        public Builder addGeneralAlert(final String alert) {
            this.mGeneralAlerts.add(alert);
            return this;
        }

        public Builder addRoute(final Route route) {
            this.mRoutes.add(route);
            return this;
        }
    }

    final static class Converter extends TypeAdapter<Station> {

        private final static String NAME_ACTUAL = "actual";
        private final static String NAME_OLD = "old";
        private final static String NAME_DIRECTIONS = "directions";
        private final static String NAME_FIRST_PASSAGE_TIME = "firstPassageTime";
        private final static String NAME_GENERAL_ALERTS = "generalAlerts";
        private final static String NAME_LAST_PASSAGE_TIME = "lastPassageTime";
        private final static String NAME_ROUTES = "routes";
        private final static String NAME_STOP_NAME = "stopName";
        private final static String NAME_STOP_SHORT_NAME = "stopShortName";
        private final TypeAdapter<List<Departure>> mDepartueListTypeAdapter;
        private final TypeAdapter<List<String>> mStringListTypeAdapter;
        private final TypeAdapter<List<Route>> mRouteListTypeAdapter;

        public Converter(Gson gson) {
            this(gson.getAdapter(GeneralTypes.DEPARTURE_LIST_TYPE_TOKEN),
                    gson.getAdapter(GeneralTypes.STRING_LIST_TYPE_TOKEN),
                    gson.getAdapter(GeneralTypes.ROUTE_LIST_TYPE_TOKEN));
        }

        public Converter(TypeAdapter<List<Departure>> departueListTypeAdapter,
                         TypeAdapter<List<String>> stringListTypeAdapter,
                         TypeAdapter<List<Route>> routeListTypeAdapter) {
            this.mDepartueListTypeAdapter = departueListTypeAdapter;
            this.mStringListTypeAdapter = stringListTypeAdapter;
            this.mRouteListTypeAdapter = routeListTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Station value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_STOP_NAME)
                    .value(value.getStopName());
            out.name(NAME_STOP_SHORT_NAME)
                    .value(value.getStopShortName());
            out.name(NAME_ROUTES);
            this.mRouteListTypeAdapter.write(out, value.getRoutes());
            out.name(NAME_ACTUAL);
            this.mDepartueListTypeAdapter.write(out, value.getActual());
            out.name(NAME_OLD);
            this.mDepartueListTypeAdapter.write(out, value.getOld());
            out.name(NAME_FIRST_PASSAGE_TIME)
                    .value(value.getFirstPassageTime());
            out.name(NAME_LAST_PASSAGE_TIME)
                    .value(value.getLastPassageTime());
            out.name(NAME_GENERAL_ALERTS);
            this.mStringListTypeAdapter.write(out, value.getGeneralAlerts());
            out.name(NAME_DIRECTIONS);
            this.mStringListTypeAdapter.write(out, value.getDirections());
            out.endObject();
        }

        @Override
        public Station read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                return null;
            }
            in.beginObject();
            Builder builder = new Builder();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(NAME_ACTUAL) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setActual(this.mDepartueListTypeAdapter.read(in));
                } else if (name.equals(NAME_OLD) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setOld(this.mDepartueListTypeAdapter.read(in));
                } else if (name.equals(NAME_DIRECTIONS) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setDirections(this.mStringListTypeAdapter.read(in));
                } else if (name.equals(NAME_FIRST_PASSAGE_TIME) && in.peek() == JsonToken.NUMBER) {
                    builder.setFirstPassageTime(in.nextLong());
                } else if (name.equals(NAME_LAST_PASSAGE_TIME) && in.peek() == JsonToken.NUMBER) {
                    builder.setLastPassageTime(in.nextLong());
                } else if (name.equals(NAME_GENERAL_ALERTS) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setGeneralAlerts(this.mStringListTypeAdapter.read(in));
                } else if (name.equals(NAME_ROUTES) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setRoutes(this.mRouteListTypeAdapter.read(in));
                } else if (name.equals(NAME_STOP_SHORT_NAME) && in.peek() == JsonToken.STRING) {
                    builder.setStopShortName(in.nextString());
                } else if (name.equals(NAME_STOP_NAME) && in.peek() == JsonToken.STRING) {
                    builder.setStopName(in.nextString());
                } else {
                    Logger.reportUnknownName(this, name, in.peek());
                    in.skipValue();
                }
            }
            in.endObject();
            return builder.build();
        }
    }
}
