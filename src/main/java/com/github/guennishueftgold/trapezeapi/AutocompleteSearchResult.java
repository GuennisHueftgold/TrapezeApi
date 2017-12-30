package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

public class AutocompleteSearchResult {
    public final static int TYPE_DIVIDER = 1, TYPE_STOP = 2, TYPE_UNKNOWN = -1;
    private final String mShortName;
    private final String mName;
    private final int mType;

    private AutocompleteSearchResult(Builder builder) {
        this.mShortName = builder.getShortName();
        this.mName = builder.getName();
        this.mType = builder.getType();
    }

    @Override
    public String toString() {
        return "AutocompleteSearchResult{" +
                "mShortName='" + mShortName + '\'' +
                ", mName='" + mName + '\'' +
                ", mType=" + mType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutocompleteSearchResult that = (AutocompleteSearchResult) o;
        return mType == that.mType &&
                Objects.equals(mShortName, that.mShortName) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mShortName, mName, mType);
    }

    public int getType() {
        return mType;
    }

    public String getShortName() {
        return mShortName;
    }

    public String getName() {
        return mName;
    }

    public static final class Builder {
        private String mShortName;
        private String mName;
        private int mType = TYPE_UNKNOWN;

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

        public int getType() {
            return mType;
        }

        public Builder setType(int type) {
            mType = type;
            return this;
        }

        public AutocompleteSearchResult build() {
            return new AutocompleteSearchResult(this);
        }
    }

    static final class Converter extends TypeAdapter<AutocompleteSearchResult> {

        private final static String NAME_NAME = "name", NAME_ID = "id", NAME_TYPE = "type";
        private final static String NAME_TYPE_STOP = "stop",
                NAME_TYPE_DIVIDER = "divider";

        @Override
        public void write(JsonWriter out, AutocompleteSearchResult value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name(NAME_NAME)
                    .value(value.getName());
            out.name(NAME_ID)
                    .value(value.getShortName());
            out.name(NAME_TYPE);
            convertTypeToJson(out, value.getType());
            out.endObject();
        }

        @Override
        public AutocompleteSearchResult read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.skipValue();
                return null;
            }
            Builder builder = new Builder();
            in.beginObject();
            String name;
            while (in.hasNext()) {
                name = in.nextName();
                switch (name) {
                    case NAME_NAME:
                        builder.setName(in.nextString());
                        break;
                    case NAME_ID:
                        builder.setShortName(in.nextString());
                        break;
                    case NAME_TYPE:
                        convertTypeFromJson(in, builder);
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

        public void convertTypeFromJson(JsonReader jsonReader, Builder builder) throws IOException {
            final String typeString = jsonReader.nextString();
            switch (typeString) {
                case NAME_TYPE_STOP:
                    builder.setType(TYPE_STOP);
                    break;
                case NAME_TYPE_DIVIDER:
                    builder.setType(TYPE_DIVIDER);
                    break;
                default:
                    Logger.reportUnknownValue(this, typeString);
                    break;
            }
        }

        public void convertTypeToJson(JsonWriter jsonWriter, int type) throws IOException {
            switch (type) {
                case TYPE_DIVIDER:
                    jsonWriter.value(NAME_TYPE_DIVIDER);
                    break;
                case TYPE_STOP:
                    jsonWriter.value(NAME_TYPE_STOP);
                    break;
                    default:
                        jsonWriter.nullValue();
                        break;
            }
        }
    }
}
