package me.frsunny.sharenews.retrofit;

import me.frsunny.sharenews.BuildConfig;
import me.frsunny.sharenews.retrofit.param.ShortenUrlParam;
import me.frsunny.sharenews.retrofit.response.ShortenUrlResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by min.namgoong@sk.com on 2016. 8. 15..
 */
public interface ShortUrlService {

    @POST("/urlshortener/v1/url?key=" + BuildConfig.URL_SHORTNER_API_KEY)
    @Headers("Content-Type: application/json")
    Call<ShortenUrlResponse> createShortenUrl(@Body ShortenUrlParam param);
}
