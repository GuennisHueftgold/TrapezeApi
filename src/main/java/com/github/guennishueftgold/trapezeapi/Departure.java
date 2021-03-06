package com.github.guennishueftgold.trapezeapi;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.joda.time.LocalTime;

import java.io.IOException;
import java.util.Objects;

/**
 * Departure information
 *
 * @since 1.0.0
 */
public final class Departure {
    private final int mActualRelativeTime;
    private final String mDirection;
    private final String mMixedTime;
    private final String mPassageId;
    private final String mPatternText;
    private final LocalTime mPlannedTime;
    private final LocalTime mActualTime;
    private final String mRouteId;
    private final int mStatus;
    private final String mTripId;
    private final String mVehicleId;

    private Departure(Builder builder) {
        this.mActualRelativeTime = builder.getActualRelativeTime();
        this.mDirection = builder.getDirection();
        this.mMixedTime = builder.getMixedTime();
        this.mPassageId = builder.getPassageId();
        this.mPatternText = builder.getPatternText();
        this.mPlannedTime = builder.getPlannedTime();
        this.mActualTime = builder.getActualTime();
        this.mRouteId = builder.getRouteId();
        this.mStatus = builder.getStatus();
        this.mTripId = builder.getTripId();
        this.mVehicleId = builder.getVehicleId();
    }

    public LocalTime getActualTime() {
        return mActualTime;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "actualRelativeTime=" + mActualRelativeTime +
                ", direction='" + mDirection + '\'' +
                ", mixedTime='" + mMixedTime + '\'' +
                ", passageId='" + mPassageId + '\'' +
                ", patternText='" + mPatternText + '\'' +
                ", plannedTime=" + mPlannedTime +
                ", actualTime=" + mActualTime +
                ", routeId='" + mRouteId + '\'' +
                ", status=" + mStatus +
                ", tripId='" + mTripId + '\'' +
                ", vehicleId='" + mVehicleId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departure departure = (Departure) o;
        return mActualRelativeTime == departure.mActualRelativeTime &&
                mStatus == departure.mStatus &&
                Objects.equals(mDirection, departure.mDirection) &&
                Objects.equals(mMixedTime, departure.mMixedTime) &&
                Objects.equals(mPassageId, departure.mPassageId) &&
                Objects.equals(mPatternText, departure.mPatternText) &&
                Objects.equals(mPlannedTime, departure.mPlannedTime) &&
                Objects.equals(mActualTime, departure.mActualTime) &&
                Objects.equals(mRouteId, departure.mRouteId) &&
                Objects.equals(mTripId, departure.mTripId) &&
                Objects.equals(mVehicleId, departure.mVehicleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mActualRelativeTime, mDirection, mMixedTime, mPassageId, mPatternText, mPlannedTime, mActualTime, mRouteId, mStatus, mTripId, mVehicleId);
    }

    public int getActualRelativeTime() {
        return mActualRelativeTime;
    }

    public String getDirection() {
        return mDirection;
    }

    public String getMixedTime() {
        return mMixedTime;
    }

    public String getPassageId() {
        return mPassageId;
    }

    public String getPatternText() {
        return mPatternText;
    }

    /**
     * gets the planned time
     * @since 1.0.0
     * @return the planned time
     */
    public LocalTime getPlannedTime() {
        return mPlannedTime;
    }

    /**
     * gets the route id
     * @since 1.0.0
     * @return the route id
     */
    public String getRouteId() {
        return mRouteId;
    }

    public int getStatus() {
        return mStatus;
    }

    /**
     * gets the trip id
     * @since 1.0.0
     * @return the trip id
     */
    public String getTripId() {
        return mTripId;
    }

    /**
     * gets the vehicle id
     * @since 1.0.0
     * @return the vehicle id
     */
    public String getVehicleId() {
        return mVehicleId;
    }

    /**
     * Builder for {@link Departure}
     * @since 1.0.0
     */
    public final static class Builder {
        private int mActualRelativeTime;
        private String mDirection;
        private String mMixedTime;
        private String mPassageId;
        private String mPatternText;
        private LocalTime mPlannedTime;
        private String mRouteId;
        private LocalTime mActualTime;
        private int mStatus = DepartureStatus.STATUS_UNKNOWN;
        private String mTripId;
        private String mVehicleId;

        public int getActualRelativeTime() {
            return mActualRelativeTime;
        }

        public Builder setActualRelativeTime(int actualRelativeTime) {
            mActualRelativeTime = actualRelativeTime;
            return this;
        }

        public LocalTime getActualTime() {
            return mActualTime;
        }

        public Builder setActualTime(LocalTime actualTime) {
            mActualTime = actualTime;
            return this;
        }

        public String getDirection() {
            return mDirection;
        }

        public Builder setDirection(String direction) {
            mDirection = direction;
            return this;
        }

        public String getMixedTime() {
            return mMixedTime;
        }

        public Builder setMixedTime(String mixedTime) {
            mMixedTime = mixedTime;
            return this;
        }

        public String getPassageId() {
            return mPassageId;
        }

        public Builder setPassageId(String passageId) {
            mPassageId = passageId;
            return this;
        }

        public String getPatternText() {
            return mPatternText;
        }

        public Builder setPatternText(String patternText) {
            mPatternText = patternText;
            return this;
        }

        public LocalTime getPlannedTime() {
            return mPlannedTime;
        }

        public Builder setPlannedTime(LocalTime plannedTime) {
            mPlannedTime = plannedTime;
            return this;
        }

        public String getRouteId() {
            return mRouteId;
        }

        public Builder setRouteId(String routeId) {
            mRouteId = routeId;
            return this;
        }

        public int getStatus() {
            return mStatus;
        }

        public Builder setStatus(int status) {
            mStatus = status;
            return this;
        }

        /**
         * @see Departure#getTripId()
         * @since 1.0.0
         * @return the trip id
         */
        public String getTripId() {
            return mTripId;
        }

        /**
         * @see Departure#getTripId()
         * @since 1.0.0
         * @param tripId the trip id
         * @return the builder
         */
        public Builder setTripId(String tripId) {
            mTripId = tripId;
            return this;
        }

        /**
         * @see Departure#getVehicleId()
         * @since 1.0.0
         * @return the vehicle id
         */
        public String getVehicleId() {
            return mVehicleId;
        }

        /**
         * @see Departure#getVehicleId()
         * @since 1.0.0
         * @param vehicleId the vehicle id
         * @return the builder
         */
        public Builder setVehicleId(String vehicleId) {
            mVehicleId = vehicleId;
            return this;
        }

        public Departure build() {
            return new Departure(this);
        }
    }

    public final static class Converter extends TypeAdapter<Departure> {
        private final static String ACTUAL_RELATIVE_TIME = "actualRelativeTime";
        private final static String PLANNED_TIME = "plannedTime";
        private final static String DIRECTION = "direction";
        private final static String MIXED_TIME = "mixedTime";
        private final static String STATUS = "status";
        private final static String PASSAGE_ID = "passageid";
        private final static String PATTERN_TEXT = "patternText";
        private final static String ROUTE_ID = "routeId";
        private final static String TRIP_ID = "tripId";
        private final static String VEHICLE_ID = "vehicleId";
        private final static String ACTUAL_TIME = "actualTime";
        private final TypeAdapter<LocalTime> mLocalTimeTypeAdapter;
        private final DepartureStatus.Converter mDepartureStatusConverter = new DepartureStatus.Converter();

        public Converter(Gson gson) {
            this(gson.getAdapter(GeneralTypes.LOCAL_TIME_TYPE_TOKEN));
        }

        public Converter(TypeAdapter<LocalTime> localTimeTypeAdapter) {
            this.mLocalTimeTypeAdapter = localTimeTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Departure value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(STATUS);
            this.mDepartureStatusConverter.write(out, value.getStatus());
            out.name(ACTUAL_RELATIVE_TIME)
                    .value(value.getActualRelativeTime());
            out.name(ACTUAL_TIME);
            this.mLocalTimeTypeAdapter.write(out, value.getActualTime());
            out.name(PLANNED_TIME);
            this.mLocalTimeTypeAdapter.write(out, value.getPlannedTime());
            out.name(MIXED_TIME).value(value.getMixedTime());
            out.name(DIRECTION).value(value.getDirection());
            out.name(PASSAGE_ID).value(value.getPassageId());
            out.name(PATTERN_TEXT).value(value.getPatternText());
            out.name(ROUTE_ID).value(value.getRouteId());
            out.name(TRIP_ID).value(value.getTripId());
            out.name(VEHICLE_ID).value(value.getVehicleId());
            out.endObject();
        }

        @Override
        public Departure read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            Departure.Builder builder = new Builder();
            in.beginObject();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(ACTUAL_RELATIVE_TIME) && in.peek() == JsonToken.NUMBER) {
                    builder.setActualRelativeTime(in.nextInt());
                } else if (name.equals(PLANNED_TIME) && in.peek() == JsonToken.STRING) {
                    builder.setPlannedTime(this.mLocalTimeTypeAdapter.read(in));
                } else if (name.equals(ACTUAL_TIME) && in.peek() == JsonToken.STRING) {
                    builder.setActualTime(this.mLocalTimeTypeAdapter.read(in));
                } else if (name.equals(MIXED_TIME) && in.peek() == JsonToken.STRING) {
                    builder.setMixedTime(in.nextString());
                } else if (name.equals(DIRECTION) && in.peek() == JsonToken.STRING) {
                    builder.setDirection(in.nextString());
                } else if (name.equals(PASSAGE_ID) && in.peek() == JsonToken.STRING) {
                    builder.setPassageId(in.nextString());
                } else if (name.equals(PATTERN_TEXT) && in.peek() == JsonToken.STRING) {
                    builder.setPatternText(in.nextString());
                } else if (name.equals(ROUTE_ID) && in.peek() == JsonToken.STRING) {
                    builder.setRouteId(in.nextString());
                } else if (name.equals(TRIP_ID) && in.peek() == JsonToken.STRING) {
                    builder.setTripId(in.nextString());
                } else if (name.equals(VEHICLE_ID) && in.peek() == JsonToken.STRING) {
                    builder.setVehicleId(in.nextString());
                } else if (name.equals(STATUS) && in.peek() == JsonToken.STRING) {
                    builder.setStatus(this.mDepartureStatusConverter.read(in));
                } else {
                    Logger.reportUnknownName(this, name, in.peek());
                    in.skipValue();
                }
            }
            in.endObject();
            return builder.build();
        }
    }
    /*{
        "actualRelativeTime": -298,
            "direction": "Wik Kanal",
            "mixedTime": "0 %UNIT_MIN%",
            "passageid": "-9187343239776208027",
            "patternText": "11",
            "plannedTime": "11:06",
            "routeId": "60835712076873989",
            "status": "DEPARTED",
            "tripId": "60839568961970697",
            "vehicleId": "60842017088864987"
    }*/
}
