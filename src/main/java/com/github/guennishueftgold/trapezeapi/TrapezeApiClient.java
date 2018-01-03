package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;


public final class TrapezeApiClient {

    public final static double COORDINATES_CONVERTION_CONSTANT = 3600000d;
    private final static long CACHE_SIZE = 1024 * 1024 * 20;
    protected final OkHttpClient mOkHttpClient;
    protected final Retrofit mRetrofit;
    protected final Gson mGson;
    protected final Cache mCache;
    protected final HttpUrl mBaseUrl;

    public TrapezeApiClient(HttpUrl baseUrl) {
        this(baseUrl, null, false);
    }

    public TrapezeApiClient(HttpUrl baseUrl, File cacheDir, boolean debug) {
        if (baseUrl == null) {
            throw new RuntimeException("BaseUrl must not be null");
        }
        this.mBaseUrl = baseUrl;
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheManipulatorInterceptor())
                .addNetworkInterceptor(new SettingsTransformInterceptor(this));
        if (cacheDir != null) {
            this.mCache = new Cache(cacheDir, TrapezeApiClient.CACHE_SIZE);
            okHttpClientBuilder.cache(this.mCache);
        } else {
            this.mCache = null;
        }
        this.mOkHttpClient = okHttpClientBuilder.build();
        this.mGson = new GsonBuilder()
                .registerTypeAdapterFactory(new TrapezeApiTypeAdapterFactory())
                .create();
        this.mRetrofit = new Retrofit.Builder()
                .client(this.mOkHttpClient)
                .baseUrl(this.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(this.mGson))
                .build();
    }

    public HttpUrl getBaseUrl() {
        return this.mBaseUrl;
    }

    public TrapezeApiService getService() {
        return this.mRetrofit.create(TrapezeApiService.class);
    }
}
