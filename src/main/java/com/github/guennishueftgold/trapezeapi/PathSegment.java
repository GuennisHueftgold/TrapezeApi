package com.github.guennishueftgold.trapezeapi;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Path Segment
 *
 * @since 1.0.0
 */
public final class PathSegment {
    private final double mLength;
    private final long mFromLongitude;
    private final long mToLongitude;
    private final long mFromLatitude;
    private final long mToLatitude;
    private final int mAngle;

    private PathSegment(Builder builder) {
        this.mFromLatitude = builder.mFromLatitude;
        this.mToLatitude = builder.mToLatitude;
        this.mToLongitude = builder.mToLongitude;
        this.mAngle = builder.mAngle;
        this.mFromLongitude = builder.mFromLongitude;
        this.mLength = builder.mLength;
    }

    /**
     * Gets the length
     *
     * @return the length
     * @since 1.0.0
     */
    public double getLength() {
        return mLength;
    }

    /**
     * Gets the longitude from where the segment started
     *
     * @return the longitude
     * @since 1.0.0
     */
    public long getFromLongitude() {
        return mFromLongitude;
    }

    /**
     * Gets the longitude to where the segment ended
     *
     * @return the longitude
     * @since 1.0.0
     */
    public long getToLongitude() {
        return mToLongitude;
    }

    /**
     * Gets the latitude from where the segment started
     *
     * @return the latitude
     * @since 1.0.0
     */
    public long getFromLatitude() {
        return mFromLatitude;
    }

    /**
     * Gets the latitude to where the segment ended
     *
     * @return the latitude
     * @since 1.0.0
     */
    public long getToLatitude() {
        return mToLatitude;
    }

    /**
     * The angle the segment is heading
     *
     * @return the angle
     * @since 1.0.0
     */
    public int getAngle() {
        return mAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathSegment that = (PathSegment) o;
        return Double.compare(that.mLength, mLength) == 0 &&
                mFromLongitude == that.mFromLongitude &&
                mToLongitude == that.mToLongitude &&
                mFromLatitude == that.mFromLatitude &&
                mToLatitude == that.mToLatitude &&
                mAngle == that.mAngle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mLength, mFromLongitude, mToLongitude, mFromLatitude, mToLatitude, mAngle);
    }

    /**
     * The Builder for {@link PathSegment}
     *
     * @since 1.0.0
     */
    public static final class Builder {
        private double mLength;
        private long mFromLongitude;
        private long mToLongitude;
        private long mFromLatitude;
        private long mToLatitude;
        private int mAngle;

        /**
         * Gets the length
         *
         * @return the length
         * @see PathSegment#getLength()
         * @since 1.0.0
         */
        public double getLength() {
            return mLength;
        }

        /**
         * Sets the length
         *
         * @param length the length of the segment
         * @return the Builder instance
         * @see PathSegment#getLength()
         * @since 1.0.0
         */
        public Builder setLength(double length) {
            mLength = length;
            return this;
        }

        /**
         * Gets the longitude
         *
         * @return the longitude
         * @see PathSegment#getFromLongitude()
         * @since 1.0.0
         */
        public long getFromLongitude() {
            return mFromLongitude;
        }

        /**
         * Sets the longitude
         *
         * @param fromLongitude the longitude
         * @return the builder instance
         * @see PathSegment#getFromLongitude()
         * @since 1.0.0
         */
        public Builder setFromLongitude(long fromLongitude) {
            mFromLongitude = fromLongitude;
            return this;
        }

        /**
         * Gets the longitude
         *
         * @return the longitude
         * @see PathSegment#getToLongitude()
         * @since 1.0.0
         */
        public long getToLongitude() {
            return mToLongitude;
        }

        /**
         * Sets the longitude
         *
         * @param toLongitude the Longitude
         * @return the Builder instance
         * @see PathSegment#getToLongitude()
         * @since 1.0.0
         */
        public Builder setToLongitude(long toLongitude) {
            mToLongitude = toLongitude;
            return this;
        }

        /**
         * Gets the latitude
         *
         * @return the latitude
         * @see PathSegment#getFromLatitude()
         * @since 1.0.0
         */
        public long getFromLatitude() {
            return mFromLatitude;
        }

        /**
         * Sets the latitude
         *
         * @param fromLatitude the latitude
         * @return the Builder instance
         * @see PathSegment#getFromLatitude()
         * @since 1.0.0
         */
        public Builder setFromLatitude(long fromLatitude) {
            mFromLatitude = fromLatitude;
            return this;
        }

        /**
         * Gets the Latitude
         *
         * @return the latitude
         * @see PathSegment#getToLatitude()
         * @since 1.0.0
         */
        public long getToLatitude() {
            return mToLatitude;
        }

        /**
         * Sets the Latitude
         *
         * @param toLatitude the latitude
         * @return the Builder instance
         * @see PathSegment#getToLatitude()
         * @since 1.0.0
         */
        public Builder setToLatitude(long toLatitude) {
            mToLatitude = toLatitude;
            return this;
        }

        /**
         * Gets the angle
         *
         * @return the angle
         * @see PathSegment#getAngle()
         * @since 1.0.0
         */
        public int getAngle() {
            return mAngle;
        }

        /**
         * Sets the angle
         *
         * @param angle the angle
         * @return the Builder instance
         * @see PathSegment#getAngle()
         * @since 1.0.0
         */
        public Builder setAngle(int angle) {
            mAngle = angle;
            return this;
        }

        /**
         * Creates a immutable PathSegment instance
         *
         * @return a immutable instance of data present in the Builder
         * @since 1.0.0
         */
        public PathSegment build() {
            return new PathSegment(this);
        }
    }

    static final class Converter extends TypeAdapter<PathSegment> {
        private final static String NAME_ANGLE = "angle",
                NAME_X1 = "x1",
                NAME_X2 = "x2",
                NAME_Y1 = "y1",
                NAME_Y2 = "y2",
                NAME_LENGTH = "length";

        @Override
        public void write(JsonWriter out, PathSegment value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_ANGLE)
                    .value(value.getAngle());
            out.name(NAME_LENGTH)
                    .value(value.getLength());
            out.name(NAME_X1)
                    .value(value.getFromLongitude());
            out.name(NAME_X2)
                    .value(value.getToLongitude());
            out.name(NAME_Y1)
                    .value(value.getFromLatitude());
            out.name(NAME_Y2)
                    .value(value.getToLatitude());
            out.endObject();
        }

        @Override
        public PathSegment read(JsonReader in) throws IOException {
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
                    case NAME_ANGLE:
                        builder.setAngle(in.nextInt());
                        break;
                    case NAME_LENGTH:
                        builder.setLength(in.nextDouble());
                        break;
                    case NAME_X1:
                        builder.setFromLongitude(in.nextLong());
                        break;
                    case NAME_X2:
                        builder.setToLongitude(in.nextLong());
                        break;
                    case NAME_Y1:
                        builder.setFromLatitude(in.nextLong());
                        break;
                    case NAME_Y2:
                        builder.setToLatitude(in.nextLong());
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
