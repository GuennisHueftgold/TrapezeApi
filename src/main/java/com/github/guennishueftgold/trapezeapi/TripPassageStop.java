package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.joda.time.LocalTime;

import java.io.IOException;
import java.util.Objects;

public final class TripPassageStop {

    public final static int STATUS_PREDICTED = 1,
            STATUS_DEPARTED = 2,
            STATUS_STOPPING = 3,
            STATUS_PLANNED = 4,
            STATUS_UNKNOWN = -1;
    private final LocalTime mPlannedTime;
    private final LocalTime mActualTime;
    private final int mStatus;
    private final String mId;
    private final String mShortName;
    private final String mName;
    private final int mStopSeqNum;

    private TripPassageStop(Builder builder) {
        this.mActualTime = builder.getActualTime();
        this.mStopSeqNum = builder.getStopSeqNum();
        this.mStatus = builder.getStatus();
        this.mName = builder.getName();
        this.mShortName = builder.getShortName();
        this.mPlannedTime = builder.getPlannedTime();
        this.mId = builder.getId();
    }

    public LocalTime getPlannedTime() {
        return mPlannedTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripPassageStop that = (TripPassageStop) o;
        return mStatus == that.mStatus &&
                mStopSeqNum == that.mStopSeqNum &&
                Objects.equals(mPlannedTime, that.mPlannedTime) &&
                Objects.equals(mActualTime, that.mActualTime) &&
                Objects.equals(mId, that.mId) &&
                Objects.equals(mShortName, that.mShortName) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mPlannedTime, mActualTime, mStatus, mId, mShortName, mName, mStopSeqNum);
    }

    public LocalTime getActualTime() {
        return mActualTime;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getId() {
        return mId;
    }

    public String getShortName() {
        return mShortName;
    }

    public String getName() {
        return mName;
    }

    public int getStopSeqNum() {
        return mStopSeqNum;
    }

    @Override
    public String toString() {
        return "TripPassageStop{" +
                "mPlannedTime=" + mPlannedTime +
                ", mActualTime=" + mActualTime +
                ", mStatus=" + mStatus +
                ", mId='" + mId + '\'' +
                ", mShortName='" + mShortName + '\'' +
                ", mName='" + mName + '\'' +
                ", mStopSeqNum=" + mStopSeqNum +
                '}';
    }


    public static class Builder {
        private LocalTime mActualTime;
        private LocalTime mPlannedTime;
        private int mStatus;
        private String mId;
        private String mShortName;
        private String mName;
        private int mStopSeqNum;

        public Builder() {

        }

        public LocalTime getPlannedTime() {
            return mPlannedTime;
        }

        public Builder setPlannedTime(LocalTime plannedTime) {
            mPlannedTime = plannedTime;
            return this;
        }

        public LocalTime getActualTime() {
            return mActualTime;
        }

        public Builder setActualTime(LocalTime actualTime) {
            mActualTime = actualTime;
            return this;
        }

        public int getStatus() {
            return mStatus;
        }

        public Builder setStatus(int status) {
            mStatus = status;
            return this;
        }

        public String getId() {
            return mId;
        }

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public String getShortName() {
            return mShortName;
        }

        public Builder setShortName(String shortName) {
            mShortName = shortName;
            return this;
        }

        public String getName() {
            return mName;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public int getStopSeqNum() {
            return mStopSeqNum;
        }

        public Builder setStopSeqNum(int stopSeqNum) {
            mStopSeqNum = stopSeqNum;
            return this;
        }

        public TripPassageStop build() {
            return new TripPassageStop(this);
        }
    }

    public final static class Converter extends TypeAdapter<TripPassageStop> {

        final static String STOP_SEQ_NUM = "stop_seq_num",
                SHORT_NAME = "shortName",
                NAME = "name",
                ID = "id",
                STOP = "stop",
                STATUS = "status",
                ACTUAL_TIME = "actualTime",
                PLANNED_TIME = "plannedTime",
                NAME_STATUS_DEPARTED = "departed",
                NAME_STATUS_PLANNED = "planned",
                NAME_STATUS_PREDICTED = "predicted",
                NAME_STATUS_STOPPING = "stopping";

        private final DepartureStatus.Converter statusTypeAdapter = new DepartureStatus.Converter();

        @Override
        public void write(JsonWriter out, TripPassageStop value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(STOP_SEQ_NUM).value(value.getStopSeqNum());
            out.name(ACTUAL_TIME);
            if (value.getActualTime() == null)
                out.nullValue();
            else
                out.value(value.getActualTime().toString());
            out.name(PLANNED_TIME);
            if (value.getPlannedTime() == null)
                out.nullValue();
            else
                out.value(value.getPlannedTime().toString());
            out.name(STATUS);
            statusTypeAdapter.write(out, value.getStatus());
            out.name(STOP).beginObject();
            out.name(ID).value(value.getId());
            out.name(NAME).value(value.getName());
            out.name(SHORT_NAME).value(value.getShortName());
            out.endObject();
            out.endObject();
        }

        @Override
        public TripPassageStop read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                return null;
            }
            in.beginObject();
            TripPassageStop.Builder tripPassageStop = new TripPassageStop.Builder();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(STOP_SEQ_NUM) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setStopSeqNum(Integer.parseInt(in.nextString()));
                } else if (name.equals(STOP_SEQ_NUM) && in.peek() == JsonToken.NUMBER) {
                    tripPassageStop.setStopSeqNum(in.nextInt());
                } else if (name.equals(ACTUAL_TIME) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setActualTime(LocalTime.parse(in.nextString()));
                } else if (name.equals(PLANNED_TIME) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setPlannedTime(LocalTime.parse(in.nextString()));
                } else if (name.equals(STATUS) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setStatus(statusTypeAdapter.read(in));
                } else if (name.equals(STOP) && in.peek() == JsonToken.BEGIN_OBJECT) {
                    this.readStop(tripPassageStop, in);
                } else {
                    Logger.reportUnknownName(this, name, in.peek());
                    in.skipValue();
                }
            }
            in.endObject();
            return tripPassageStop.build();
        }

        private void readStop(TripPassageStop.Builder tripPassageStop, JsonReader in) throws IOException {
            in.beginObject();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                if (name.equals(ID) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setId(in.nextString());
                } else if (name.equals(SHORT_NAME) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setShortName(in.nextString());
                } else if (name.equals(NAME) && in.peek() == JsonToken.STRING) {
                    tripPassageStop.setName(in.nextString());
                } else {
                    Logger.reportUnknownName(this, name, in.peek());
                    in.skipValue();
                }
            }
            in.endObject();
        }

    }
}
