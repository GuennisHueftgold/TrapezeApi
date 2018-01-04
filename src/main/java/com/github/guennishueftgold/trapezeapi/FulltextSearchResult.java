package com.github.guennishueftgold.trapezeapi;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @since 1.0.0
 */
public final class FulltextSearchResult {
    private final String mShortName;
    private final String mName;

    private FulltextSearchResult(Builder builder) {
        this.mShortName = builder.getShortName();
        this.mName = builder.getName();
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

    /**
     * Gets the name
     *
     * @return the name
     * @since 1.0.0
     */
    public String getName() {
        return mName;
    }

    /**
     * @param o other object
     * @return true if both objects are equal
     * @since 1.3.0
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FulltextSearchResult)) return false;

        FulltextSearchResult that = (FulltextSearchResult) o;

        if (mShortName != null ? !mShortName.equals(that.mShortName) : that.mShortName != null) return false;
        return mName != null ? mName.equals(that.mName) : that.mName == null;
    }

    /**
     * Returns the hashCode
     *
     * @return the hashcode
     * @since 1.3.0
     */
    @Override
    public int hashCode() {
        int result = mShortName != null ? mShortName.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FulltextSearchResult{" +
                "shortName='" + mShortName + '\'' +
                ", name='" + mName + '\'' +
                '}';
    }

    /**
     * Builder for {@link FulltextSearchResult}
     *
     * @since 1.3.0
     */
    public static final class Builder {
        private String mName;
        private String mShortName;

        /**
         * Gets the Name
         *
         * @return the name
         * @see FulltextSearchResult#getName()
         * @since 1.3.0
         */
        public String getName() {
            return mName;
        }

        /**
         * Sets the Name
         *
         * @param name the name
         * @return the Builder Instance
         * @see FulltextSearchResult#getName()
         * @since 1.3.0
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Gets the short name
         *
         * @return the short name
         * @see FulltextSearchResult#getShortName()
         * @since 1.3.0
         */
        public String getShortName() {
            return mShortName;
        }

        /**
         * Sets the short name
         *
         * @param shortName the short name
         * @return the short name
         * @see FulltextSearchResult#getShortName()
         * @since 1.3.0
         */
        public Builder setShortName(String shortName) {
            mShortName = shortName;
            return this;
        }

        /**
         * Generates a immutable {@link FulltextSearchResult} instance
         *
         * @return a FullTextSearchResult
         * @since 1.3.0
         */
        public FulltextSearchResult build() {
            return new FulltextSearchResult(this);
        }
    }

    /**
     * @since 1.3.0
     */
    static final class Converter extends TypeAdapter<FulltextSearchResult> {
        final static String NAME_SHORT_NAME = "stop",
                NAME_NAME = "stopPassengerName";

        @Override
        public void write(JsonWriter jsonWriter, FulltextSearchResult fulltextSearchResult) throws IOException {
            if (fulltextSearchResult == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(NAME_SHORT_NAME)
                    .value(fulltextSearchResult.getShortName());
            jsonWriter.name(NAME_NAME)
                    .value(fulltextSearchResult.getName());
            jsonWriter.endObject();
        }

        @Override
        public FulltextSearchResult read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                return null;
            }
            FulltextSearchResult.Builder builder = new Builder();
            jsonReader.beginObject();
            String name;
            while (jsonReader.hasNext()) {
                name = jsonReader.nextName();
                switch (name) {
                    case NAME_SHORT_NAME:
                        builder.setShortName(jsonReader.nextString());
                        break;
                    case NAME_NAME:
                        builder.setName(jsonReader.nextString());
                        break;
                    default:
                        Logger.reportUnknownName(this, name, jsonReader.peek());
                        jsonReader.skipValue();
                        break;
                }
            }
            jsonReader.endObject();
            return builder.build();
        }
    }
}
