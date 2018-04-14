package com.tonal.test.data.remote;

import com.tonal.test.data.model.Results;
import com.tonal.test.utils.APIUtils;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sonal on 4/13/18.
 */

public interface APIService {

    @GET("forecast")
    public Single<Results> getWeatherData(@Query("zip") String zipcode, @Query("appid") String appId
            , @Query("units") String units);

    public class Creator {

        public static APIService create() {

            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(APIUtils.getBaseUrl()).build();


            return retrofit.create(APIService.class);

        }
    }
}
