package com.hthh.bookapp.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hthh.bookapp.Key;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static APIController service;

    public static APIController getService() {
        if (service == null) {
            new RetrofitClient();
        }
        return service;
    }

    private RetrofitClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Key.BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson));

        service = builder.build().create(APIController.class);
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
}
