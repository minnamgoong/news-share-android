package me.frsunny.sharenews.retrofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import me.frsunny.sharenews.BuildConfig;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by min.namgoong@sk.com on 2016. 8. 15..
 */
public class RestClient {
    public static final String API_URL = "https://www.googleapis.com";

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 5000;

    private final OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public ShortUrlService shortUrlService;

//    private Converter<ResponseBody, ErrorResponse> errorConverter;

    private RestClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);

        if (API_URL.contains("https://")) {
            builder.connectionSpecs(Collections.singletonList(ConnectionSpec.COMPATIBLE_TLS)); // support https, TLS 1.0 for Android 4.x (TLS 1.2 is supported over Android 5.0)
        }

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        okHttpClient = builder.build();

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();

        shortUrlService = retrofit.create(ShortUrlService.class);
//        errorConverter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[]{});
    }

//    public Converter<ResponseBody, ErrorResponse> getErrorConverter() {
//        return errorConverter;
//    }

    private static class RetrofitSingletonHolder {
        static final RestClient instance = new RestClient();
    }

    public static RestClient getInstance() {
        return RetrofitSingletonHolder.instance;
    }
}
