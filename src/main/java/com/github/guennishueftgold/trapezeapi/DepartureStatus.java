package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public interface DepartureStatus {
    int STATUS_PREDICTED = 1,
            STATUS_DEPARTED = 2,
            STATUS_STOPPING = 3,
            STATUS_PLANNED = 4,
            STATUS_UNKNOWN = -1;

    final class Converter extends TypeAdapter<Integer> {
        private final static String
                NAME_STATUS_DEPARTED = "DEPARTED",
                NAME_STATUS_PLANNED = "PLANNED",
                NAME_STATUS_PREDICTED = "PREDICTED",
                NAME_STATUS_STOPPING = "STOPPING";

        @Override
        public void write(JsonWriter jsonWriter, Integer integer) throws IOException {
            if (integer == null) {
                jsonWriter.nullValue();
                return;
            }
            switch (integer) {
                case STATUS_DEPARTED:
                    jsonWriter.value(NAME_STATUS_DEPARTED);
                    break;
                case STATUS_PLANNED:
                    jsonWriter.value(NAME_STATUS_PLANNED);
                    break;
                case STATUS_STOPPING:
                    jsonWriter.value(NAME_STATUS_STOPPING);
                    break;
                case STATUS_PREDICTED:
                    jsonWriter.value(NAME_STATUS_PREDICTED);
                    break;
                default:
                    jsonWriter.nullValue();
                    break;
            }
        }

        @Override
        public Integer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                return STATUS_UNKNOWN;
            }
            final String value = jsonReader.nextString();
            switch (value.toUpperCase()) {
                case NAME_STATUS_DEPARTED:
                    return STATUS_DEPARTED;
                case NAME_STATUS_PREDICTED:
                    return STATUS_PREDICTED;
                case NAME_STATUS_STOPPING:
                    return STATUS_STOPPING;
                case NAME_STATUS_PLANNED:
                    return STATUS_PLANNED;
                default:
                    Logger.reportUnknownValue(this, value);
                    return STATUS_UNKNOWN;
            }
        }
    }
}
