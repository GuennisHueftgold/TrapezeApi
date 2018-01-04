package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Logger that can be used to debug the used {@link TypeAdapter}s being used by the library
 *
 * @since 1.0.0
 */
public abstract class Logger {

    @CheckForNull
    private static Logger sLogger;

    /**
     * Gets the current {@link Logger} instance
     *
     * @return logger instance or null
     * @since 1.0.0
     */
    @Nullable
    public static Logger getInstance() {
        return sLogger;
    }

    /**
     * Sets the current Logger instance
     *
     * @param logger the logger instance or null
     * @since 1.0.0
     */
    public static void setInstance(@Nullable Logger logger) {
        sLogger = logger;
    }

    /**
     * Report a unknown name inside a {@link TypeAdapter#read(JsonReader)}
     *
     * @param typeAdapter the typeadapter
     * @param name        the name that is unknown
     * @param jsonToken   the jsontoken of the unknown name
     * @since 1.0.0
     */
    public static void reportUnknownName(@Nonnull TypeAdapter typeAdapter, @Nonnull String name, @Nonnull JsonToken jsonToken) {
        if (sLogger != null)
            sLogger.unknownName(typeAdapter, name, jsonToken);
    }

    /**
     * Report a unknown vallue inside a {@link TypeAdapter#read(JsonReader)}
     *
     * @param typeAdapter the typeadapter
     * @param value       the value
     * @since 1.0.0
     */
    public static void reportUnknownValue(@Nonnull TypeAdapter typeAdapter, @Nonnull String value) {
        if (sLogger != null)
            sLogger.unknownValue(typeAdapter, value);
    }

    /**
     * Implement this to log the reported missing name
     *
     * @param typeAdapter the typeadapter
     * @param name        the name
     * @param jsonToken   the jsontoken
     * @see Logger#reportUnknownName(TypeAdapter, String, JsonToken)
     * @since 1.0.0
     */
    public abstract void unknownName(@Nonnull TypeAdapter typeAdapter, @Nonnull String name, @Nonnull JsonToken jsonToken);

    /**
     * Implement this to log the reported missing value
     *
     * @param typeAdapter the typeadapter
     * @param value       the value
     * @see Logger#reportUnknownValue(TypeAdapter, String)
     * @since 1.0.0
     */
    public abstract void unknownValue(@Nonnull TypeAdapter typeAdapter, @Nonnull String value);
}
