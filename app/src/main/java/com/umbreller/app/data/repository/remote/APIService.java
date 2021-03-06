package com.umbreller.app.data.repository.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umbreller.app.data.entity.WeatherEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * APIService for retrieving data from the network using Retrofit.
 */
public interface APIService {

  String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
  String API_KEY = "d8f91422daf1af27a31fb2cc58173c16";

  @GET("forecast")
  Observable<Response<WeatherEntity>> getWeather(@Query("lat") String latitude, @Query("lon") String
      longitude, @Query("appid") String apiKey);

  /********
   * Helper class that sets up a new services
   *******/
  class Creator {

    private static OkHttpClient createHttpClient() {
      return new OkHttpClient.Builder()
          .readTimeout(10000, TimeUnit.MILLISECONDS)
          .connectTimeout(15000, TimeUnit.MILLISECONDS)
          .build();
    }

    public static APIService newAPIService() {
      Gson gson = new GsonBuilder()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(API_BASE_URL)
          .client(Creator.createHttpClient())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();
      return retrofit.create(APIService.class);
    }
  }
}
