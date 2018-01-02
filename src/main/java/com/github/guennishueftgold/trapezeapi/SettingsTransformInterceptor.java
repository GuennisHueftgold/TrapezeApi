package com.github.guennishueftgold.trapezeapi;

import okhttp3.*;
import okio.BufferedSource;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;

import java.io.IOException;
import java.util.Objects;

final class SettingsTransformInterceptor implements Interceptor {
    final static MediaType CONTENT_TYPE_JAVASCRIPT = MediaType.parse("text/javascript");
    final static MediaType CONTENT_TYPE_JSON = MediaType.parse("application/json");
    final static String HEADER_CONTENT_TYPE = "Content-Type";
    final static ByteString START_MARKER = ByteString.encodeUtf8("{");
    final static String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Response response = chain.proceed(request);
        if (!request.method().equalsIgnoreCase("get"))
            return response;
        if (response.code() != 200)
            return response;
        if (!request.url().encodedPath().endsWith("/settings"))
            return response;
        final MediaType contentType = response.body().contentType();
        if (CONTENT_TYPE_JAVASCRIPT.equals(contentType)) {
            BufferedSource source;
            if (Objects.equals(response.header(HEADER_CONTENT_ENCODING, null), "gzip")) {
                source = Okio.buffer(new GzipSource(response.body().source()));
            } else {
                source = response.body().source();
            }
            final long start = source.indexOf(START_MARKER);
            source.skip(start);
            return response
                    .newBuilder()
                    .body(ResponseBody.create(CONTENT_TYPE_JSON, source.readUtf8()))
                    .removeHeader(HEADER_CONTENT_ENCODING)
                    .build();
        }
        return response;
    }
}
