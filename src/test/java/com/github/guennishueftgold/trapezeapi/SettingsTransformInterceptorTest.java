package com.github.guennishueftgold.trapezeapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import okio.ByteString;
import okio.GzipSink;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SettingsTransformInterceptorTest {
    final static String TEST_VALUE = "{\"name\":\"value\"}";
    final static String TEST_VALUE_ORIGINAL = "INITIAL_VALUES =" + TEST_VALUE;

    static final MockResponse createCorrectResponse() {
        final MockResponse mockResponse =
                new MockResponse();
        mockResponse.setResponseCode(200);
        mockResponse.setBody(TEST_VALUE_ORIGINAL);
        mockResponse.setBodyDelay(100, TimeUnit.MILLISECONDS);
        mockResponse.setHeader("Content-Type", SettingsTransformInterceptor.CONTENT_TYPE_JAVASCRIPT.toString());
        return mockResponse;
    }

    @Test
    public void should_parse_correctly() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(createCorrectResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Response response = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/settings")).build()).execute();
        assertEquals(TEST_VALUE, response.body().string());
        mockWebServer.shutdown();
    }

    @Test
    public void should_parse_gziped_correctly() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        final MockResponse mockResponse = createCorrectResponse();
        mockResponse.setHeader(SettingsTransformInterceptor.HEADER_CONTENT_ENCODING, "gzip");
        Buffer input = new Buffer();
        input.write(ByteString.encodeUtf8(TEST_VALUE_ORIGINAL));
        Buffer output = new Buffer();
        GzipSink gzipSink = new GzipSink(output);
        gzipSink.write(input, input.size());
        gzipSink.flush();
        gzipSink.close();
        mockResponse.setBody(output);
        mockWebServer.enqueue(mockResponse);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Response response = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/settings")).build()).execute();
        assertEquals(TEST_VALUE, response.body().string());
        mockWebServer.shutdown();
    }

    @Test
    public void should_skip_non_success_response_codes() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        final String USELESS_BODY = "useless_body";
        final MockResponse mockResponse =
                new MockResponse();
        mockResponse.setResponseCode(404);
        mockResponse.setBody(USELESS_BODY);
        mockWebServer.enqueue(mockResponse);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        Response response = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/settings")).build()).execute();
        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals(USELESS_BODY, response.body().string());
        assertEquals(response.code(), 404);
        mockWebServer.shutdown();
    }

    @Test
    public void should_skip_none_get_requests() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(createCorrectResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Request request = new Request.Builder()
                .url(mockWebServer.url("/settings"))
                .method("delete", null)
                .build();
        final Response response = okHttpClient.newCall(request).execute();
        assertEquals(TEST_VALUE_ORIGINAL, response.body().string());
        mockWebServer.shutdown();
    }

    @Test
    public void should_skip_none_settings_requests() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(createCorrectResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Request request = new Request.Builder()
                .url(mockWebServer.url("/settings2"))
                .build();
        final Response response = okHttpClient.newCall(request).execute();
        assertEquals(TEST_VALUE_ORIGINAL, response.body().string());
        mockWebServer.shutdown();
    }

    @Test
    public void should_skip_json_content_requests() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        MockResponse mockResponse = createCorrectResponse();
        mockResponse.setBody(TEST_VALUE);
        mockResponse.setHeader(SettingsTransformInterceptor.HEADER_CONTENT_TYPE, SettingsTransformInterceptor.CONTENT_TYPE_JSON.toString());
        mockWebServer.enqueue(mockResponse);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Request request = new Request.Builder()
                .url(mockWebServer.url("/settings"))
                .build();
        final Response response = okHttpClient.newCall(request).execute();
        assertEquals(TEST_VALUE, response.body().string());
        assertEquals(response.header(SettingsTransformInterceptor.HEADER_CONTENT_TYPE, null),
                SettingsTransformInterceptor.CONTENT_TYPE_JSON.toString());
        mockWebServer.shutdown();
    }



}
