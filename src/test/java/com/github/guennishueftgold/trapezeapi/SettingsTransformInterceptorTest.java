package com.github.guennishueftgold.trapezeapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SettingsTransformInterceptorTest {
    @Test
    public void should_parse_correctly() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        final String TEST_VALUE = "{\"name\":\"value\"}";
        final MockResponse mockResponse =
                new MockResponse();
        mockResponse.setResponseCode(200);
        mockResponse.setBody("INITIAL_VALUES =" + TEST_VALUE);
        mockResponse.setHeader("Content-Type", SettingsTransformInterceptor.CONTENT_TYPE_JAVASCRIPT.toString());
        mockWebServer.enqueue(mockResponse);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new SettingsTransformInterceptor()).build();
        final Response response = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/settings")).build()).execute();
        mockWebServer.shutdown();
        assertEquals(TEST_VALUE, response.body().string());
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
        mockWebServer.shutdown();
        assertEquals(USELESS_BODY, response.body().string());
        assertEquals(response.code(), 404);
    }
}
