package com.github.guennishueftgold.trapezeapi;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.joda.time.LocalTime;

import java.io.IOException;
import java.util.Objects;

public class Departure {
    public final static int STATUS_DEPARTED = 1;
    public final static int STATUS_PREDICTED = 2;
    public final static int STATUS_PLANNED = 3;
    public final static int STATUS_STOPPING = 4;
    public final static int STATUS_UNKNOWN = 0;
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

    public Departure(Builder builder) {
        this.mActualRelativeTime = builder.mActualRelativeTime;
        this.mDirection = builder.mDirection;
        this.mMixedTime = builder.mMixedTime;
        this.mPassageId = builder.mPassageId;
        this.mPatternText = builder.mPatternText;
        this.mPlannedTime = builder.mPlannedTime;
        this.mActualTime = builder.mActualTime;
        this.mRouteId = builder.mRouteId;
        this.mStatus = builder.mStatus;
        this.mTripId = builder.mTripId;
        this.mVehicleId = builder.mVehicleId;
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

    public LocalTime getPlannedTime() {
        return mPlannedTime;
    }

    public String getRouteId() {
        return mRouteId;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getTripId() {
        return mTripId;
    }

    public String getVehicleId() {
        return mVehicleId;
    }


    public final static class Builder {
        private int mActualRelativeTime;
        private String mDirection;
        private String mMixedTime;
        private String mPassageId;
        private String mPatternText;
        private LocalTime mPlannedTime;
        private String mRouteId;
        private LocalTime mActualTime;
        private int mStatus = STATUS_UNKNOWN;
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

        public String getTripId() {
            return mTripId;
        }

        public Builder setTripId(String tripId) {
            mTripId = tripId;
            return this;
        }

        public String getVehicleId() {
            return mVehicleId;
        }

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
            this.mDepartureStatusConverter.write(out, value.mStatus);
            out.name(ACTUAL_RELATIVE_TIME)
                    .value(value.mActualRelativeTime);
            out.name(ACTUAL_TIME);
            this.mLocalTimeTypeAdapter.write(out, value.mActualTime);
            out.name(PLANNED_TIME);
            this.mLocalTimeTypeAdapter.write(out, value.mPlannedTime);
            out.name(MIXED_TIME).value(value.mMixedTime);
            out.name(DIRECTION).value(value.mDirection);
            out.name(PASSAGE_ID).value(value.mPassageId);
            out.name(PATTERN_TEXT).value(value.mPatternText);
            out.name(ROUTE_ID).value(value.mRouteId);
            out.name(TRIP_ID).value(value.mTripId);
            out.name(VEHICLE_ID).value(value.mVehicleId);
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
                    in.skipValue();
                    Timber.d("Skipped value for: " + name);
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
