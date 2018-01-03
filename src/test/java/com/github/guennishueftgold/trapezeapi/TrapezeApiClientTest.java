package com.github.guennishueftgold.trapezeapi;

import org.junit.Test;

public class TrapezeApiClientTest {

    @Test(expected = Exception.class)
    public void should_throw_error_if_baseurl_is_null_in_constructor1() {
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(null);
    }

    @Test(expected = Exception.class)
    public void should_throw_error_if_baseurl_is_null_in_constructor2() {
        TrapezeApiClient trapezeApiClient = new TrapezeApiClient(null, null, false);
    }
}
