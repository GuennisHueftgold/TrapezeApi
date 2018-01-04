package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class Route {
    public final static int ROUTE_TYPE_BUS = 1;
    public final static int ROUTE_TYPE_UNKNOWN = -1;
    private final List<String> mAlerts;
    private final String mAuthority;
    private final List<String> mDirections;
    private final String mId;
    private final String mName;
    private final int mRouteType;
    private final String mShortName;

    private Route(Builder builder) {
        this.mAlerts = builder.getAlerts();
        this.mAuthority = builder.getAuthority();
        this.mDirections = builder.getDirections();
        this.mId = builder.getId();
        this.mName = builder.getName();
        this.mRouteType = builder.getRouteType();
        this.mShortName = builder.getShortName();
    }

    @Override
    public String toString() {
        return "Route{" +
                "mAlerts=" + mAlerts +
                ", mAuthority='" + mAuthority + '\'' +
                ", mDirections=" + mDirections +
                ", mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mRouteType=" + mRouteType +
                ", mShortName='" + mShortName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return mRouteType == route.mRouteType &&
                Objects.equals(mAlerts, route.mAlerts) &&
                Objects.equals(mAuthority, route.mAuthority) &&
                Objects.equals(mDirections, route.mDirections) &&
                Objects.equals(mId, route.mId) &&
                Objects.equals(mName, route.mName) &&
                Objects.equals(mShortName, route.mShortName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mAlerts, mAuthority, mDirections, mId, mName, mRouteType, mShortName);
    }

    public List<String> getAlerts() {
        return mAlerts;
    }

    public String getAuthority() {
        return mAuthority;
    }

    public List<String> getDirections() {
        return mDirections;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    /**
     * Gets the route type.
     * Can be any of: {@link Route#ROUTE_TYPE_BUS} or {@link Route#ROUTE_TYPE_UNKNOWN}
     *
     * @return the route type
     * @since 1.0.0
     */
    public int getRouteType() {
        return mRouteType;
    }

    /**
     * Gets the short name
     *
     * @return the short name
     * @since 1.0.0
     */
    public String getShortName() {
        return mShortName;
    }

    public final static class Builder {

        private List<String> mAlerts;
        private String mAuthority;
        private List<String> mDirections;
        private String mId;
        private String mName;

        private int mRouteType;
        private String mShortName;

        public List<String> getAlerts() {
            return mAlerts;
        }

        public Builder setAlerts(List<String> alerts) {
            mAlerts = alerts;
            return this;
        }

        public String getAuthority() {
            return mAuthority;
        }

        public Builder setAuthority(String authority) {
            mAuthority = authority;
            return this;
        }

        public List<String> getDirections() {
            return mDirections;
        }

        public Builder setDirections(List<String> directions) {
            mDirections = directions;
            return this;
        }

        public String getId() {
            return mId;
        }

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public String getName() {
            return mName;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Gets the route Type
         *
         * @return the route type
         * @see Route#getRouteType()
         * @since 1.0.0
         */
        public int getRouteType() {
            return mRouteType;
        }

        /**
         * Sets the route type
         *
         * @param routeType the route type
         * @return the builder instance
         * @see Route#getRouteType()
         * @since 1.0.0
         */
        public Builder setRouteType(int routeType) {
            mRouteType = routeType;
            return this;
        }

        /**
         * Gets the short name
         *
         * @return the short name
         * @see Route#getShortName()
         * @since 1.0.0
         */
        public String getShortName() {
            return mShortName;
        }

        /**
         * Sets the shortname
         *
         * @param shortName short name
         * @return the builder instance
         * @see Route#getShortName()
         * @since 1.0.0
         */
        public Builder setShortName(String shortName) {
            mShortName = shortName;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }

    static final class Converter extends TypeAdapter<Route> {
        private final static String NAME_ALERTS = "alerts",
                NAME_AUTHORITY = "authority",
                NAME_DIRECTIONS = "directions",
                NAME_ID = "id",
                NAME_NAME = "name",
                NAME_ROUTE_TYPE = "routeType",
                NAME_SHORT_NAME = "shortName",
                NAME_ROUTE_TYPE_BUS = "bus";
        private final TypeToken<List<String>> STRING_LIST_TYPE_TOKEN = new TypeToken<List<String>>() {
        };
        private final TypeAdapter<List<String>> mStringListTypeAdapter;

        public Converter(Gson gson) {
            this.mStringListTypeAdapter = gson.getAdapter(STRING_LIST_TYPE_TOKEN);
        }

        @Override
        public void write(JsonWriter out, Route value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_ALERTS);
            this.mStringListTypeAdapter.write(out, value.getAlerts());
            out.name(NAME_DIRECTIONS);
            this.mStringListTypeAdapter.write(out, value.getDirections());
            out.name(NAME_AUTHORITY)
                    .value(value.getAuthority());
            out.name(NAME_ID)
                    .value(value.getId());
            out.name(NAME_NAME)
                    .value(value.getName());
            out.name(NAME_SHORT_NAME)
                    .value(value.getShortName());
            out.name(NAME_ROUTE_TYPE);
            switch (value.getRouteType()) {
                case ROUTE_TYPE_BUS:
                    out.value(NAME_ROUTE_TYPE_BUS);
                    break;
                default:
                    out.nullValue();
                    break;
            }
            out.endObject();
        }

        @Override
        public Route read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            Route.Builder builder = new Builder();
            in.beginObject();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(NAME_ALERTS) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setAlerts(this.mStringListTypeAdapter.read(in));
                } else if (name.equals(NAME_AUTHORITY) && in.peek() == JsonToken.STRING) {
                    builder.setAuthority(in.nextString());
                } else if (name.equals(NAME_DIRECTIONS) && in.peek() == JsonToken.BEGIN_ARRAY) {
                    builder.setDirections(this.mStringListTypeAdapter.read(in));
                } else if (name.equals(NAME_ID) && in.peek() == JsonToken.STRING) {
                    builder.setId(in.nextString());
                } else if (name.equals(NAME_NAME) && in.peek() == JsonToken.STRING) {
                    builder.setName(in.nextString());
                } else if (name.equals(NAME_ROUTE_TYPE) && in.peek() == JsonToken.STRING) {
                    final String routeType = in.nextString().toLowerCase();
                    switch (routeType) {
                        case NAME_ROUTE_TYPE_BUS:
                            builder.setRouteType(ROUTE_TYPE_BUS);
                            break;
                        default:
                            builder.setRouteType(ROUTE_TYPE_UNKNOWN);
                            in.skipValue();
                            Logger.reportUnknownValue(this, routeType);
                            break;
                    }
                } else if (name.equals(NAME_SHORT_NAME) && in.peek() == JsonToken.STRING) {
                    builder.setShortName(in.nextString());
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
