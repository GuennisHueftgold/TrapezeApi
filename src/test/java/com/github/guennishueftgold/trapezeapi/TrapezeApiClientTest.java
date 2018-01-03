package com.github.guennishueftgold.trapezeapi;

import okhttp3.HttpUrl;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TrapezeApiClientTest {

    @Test(expected = Exception.class)
    public void should_throw_error_if_baseurl_is_null_in_constructor1() {
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(null);
    }

    @Test(expected = Exception.class)
    public void should_throw_error_if_baseurl_is_null_in_constructor2() {
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(null, null, false);
    }

    @Test
    public void should_set_baseUrl_correctly() {
        final HttpUrl baseUrl = HttpUrl.parse("https://test.com/test/");
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(baseUrl);
        assertNull(trapezeApiClient.mCache);
        assertEquals(trapezeApiClient.mBaseUrl, baseUrl);
        assertEquals(trapezeApiClient.mRetrofit.baseUrl(), baseUrl);
    }

    @Test
    public void should_set_cache_correctly() {
        final HttpUrl baseUrl = HttpUrl.parse("https://test.com/test/");
        final File file = new File("/random/path");
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(baseUrl, file, false);
        assertEquals(trapezeApiClient.mBaseUrl, baseUrl);
        assertEquals(trapezeApiClient.mRetrofit.baseUrl(), baseUrl);
        assertNotNull(trapezeApiClient.mCache);
        assertEquals(trapezeApiClient.mCache.directory(), file);
    }

    @Test
    public void should_get_service() {
        final HttpUrl baseUrl = HttpUrl.parse("https://test.com/test/");
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(baseUrl);
        assertNotNull(trapezeApiClient.getService());
    }
}
