package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

/**
 * The main entry point for interaction with the api
 */
public final class TrapezeApiClient {

    public final static double COORDINATES_CONVERTION_CONSTANT = 3600000d;
    private final static long CACHE_SIZE = 1024 * 1024 * 20;
    protected final OkHttpClient mOkHttpClient;
    protected final Retrofit mRetrofit;
    protected final Gson mGson;
    @CheckForNull
    protected final Cache mCache;
    protected final HttpUrl mBaseUrl;

    /**
     * Constructs a {@link TrapezeApiClient} instance
     *
     * @param baseUrl the base url for the api endpoint
     * @since 1.2.0
     */
    public TrapezeApiClient(@Nonnull HttpUrl baseUrl) {
        this(baseUrl, null, false);
    }

    /**
     * Constructs a {@link TrapezeApiClient} instance
     *
     * @param baseUrl  the base url for the api endpoint
     * @param cacheDir the cacheDir to be used
     * @param debug    should print debug information
     * @since 1.2.0
     */
    public TrapezeApiClient(@Nonnull HttpUrl baseUrl, @Nullable File cacheDir, boolean debug) {
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

    /**
     * The base url being used by the Api Client
     *
     * @return the base url
     * @apiNote this method should never be null, as the {@link TrapezeApiClient} cannot be constructed without a valid url
     * @since 1.2.0
     */
    @Nonnull
    public HttpUrl getBaseUrl() {
        return this.mBaseUrl;
    }

    /**
     * The Endpoints Service provided by the Api Client
     *
     * @return the endpoint service
     * @since 1.0.0
     */
    @Nonnull
    public TrapezeApiService getService() {
        return this.mRetrofit.create(TrapezeApiService.class);
    }
}
