package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Logger that can be used to debug the used {@link TypeAdapter}s being used by the library
 */
public abstract class Logger {

    private static Logger sLogger;

    public static Logger getInstance() {
        return sLogger;
    }

    public static void setInstance(Logger logger) {
        sLogger = logger;
    }

    /**
     * Report a unknown name inside a {@link TypeAdapter#read(JsonReader)}
     *
     * @param typeAdapter the typeadapter
     * @param name        the name that is unknown
     * @param jsonToken   the jsontoken of the unknown name
     */
    public static void reportUnknownName(TypeAdapter typeAdapter, String name, JsonToken jsonToken) {
        if (sLogger != null)
            sLogger.unknownName(typeAdapter, name, jsonToken);
    }

    /**
     * Report a unknown vallue inside a {@link TypeAdapter#read(JsonReader)}
     * @param typeAdapter the typeadapter
     * @param value the value
     */
    public static void reportUnknownValue(TypeAdapter typeAdapter, String value) {
        if (sLogger != null)
            sLogger.unknownValue(typeAdapter, value);
    }

    /**
     * Implement this to log the reported missing name
     * @see Logger#reportUnknownName(TypeAdapter, String, JsonToken)
     * @since 1.0.0
     * @param typeAdapter the typeadapter
     * @param name the name
     * @param jsonToken the jsontoken
     */
    public abstract void unknownName(TypeAdapter typeAdapter, String name, JsonToken jsonToken);

    /**
     * Implement this to log the reported missing value
     * @see Logger#reportUnknownValue(TypeAdapter, String)
     * @see 1.0.0
     * @param typeAdapter the typeadapter
     * @param value the value
     */
    public abstract void unknownValue(TypeAdapter typeAdapter, String value);
}
