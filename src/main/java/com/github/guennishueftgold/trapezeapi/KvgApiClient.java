package com.github.guennishueftgold.trapezeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;


public final class KvgApiClient {

    public final static double COORDINATES_CONVERTION_CONSTANT = 3600000d;
    private final static long CACHE_SIZE = 1024 * 1024 * 20;
    private final static String API_BASE_URL = "http://www.kvg-kiel.de/internetservice/";
    private static KvgApiClient mInstance;
    private final OkHttpClient mOkHttpClient;
    private final Retrofit mRetrofit;
    private final Gson mGson;
    private final Cache mCache;

    public KvgApiClient() {
        this(null, false);
    }

    public KvgApiClient(File cacheDir, boolean debug) {
        this.mCache = new Cache(cacheDir, KvgApiClient.CACHE_SIZE);
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .cache(this.mCache)
                .addNetworkInterceptor(new CacheManipulatorInterceptor());
        if (debug) {
            /*
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClientBuilder.addNetworkInterceptor(logging);*/
        }
        this.mOkHttpClient = okHttpClientBuilder.build();
        this.mGson = new GsonBuilder()
                .registerTypeAdapterFactory(new KvgApiTypeAdapterFactory())
                .create();
        this.mRetrofit = new Retrofit.Builder()
                .client(this.mOkHttpClient)
                .baseUrl(KvgApiClient.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(this.mGson))
                .build();
    }

    public static KvgApiClient getInstance() {
        return KvgApiClient.mInstance;
    }

    public final static void init(File cacheDir, boolean debug) {
        if (KvgApiClient.mInstance != null) {
            return;
        }
        KvgApiClient.mInstance = new KvgApiClient(cacheDir, debug);
    }

    public KvgApiService getService() {
        return this.mRetrofit.create(KvgApiService.class);
    }
}
