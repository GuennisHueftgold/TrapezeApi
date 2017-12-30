package com.github.guennishueftgold.trapezeapi;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;

public abstract class Logger {

    private static Logger sLogger;

    public static Logger getInstance() {
        return sLogger;
    }

    public static void setInstance(Logger logger) {
        sLogger = logger;
    }

    public static void reportUnknownName(TypeAdapter typeAdapter, String name, JsonToken jsonToken) {
        if (sLogger != null)
            sLogger.unknownName(typeAdapter, name, jsonToken);
    }

    public abstract void unknownName(TypeAdapter typeAdapter, String name, JsonToken jsonToken);

    public static void d(String tag, Object... args){

    }

    public static void reportUnknownValue(TypeAdapter typeAdapter, String value) {
        if (sLogger != null)
            sLogger.unknownValue(typeAdapter, value);
    }

    public abstract void unknownValue(TypeAdapter typeAdapter, String value);
}
