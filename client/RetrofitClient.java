package com.example.sample.client;

import com.example.sample.api.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASEURL = "https://jsonplaceholder.typicode.com/";
    private Retrofit retrofit;
    private static RetrofitClient minsttance = null;


    public RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL)
                .build();
    }

    public static RetrofitClient getInstance() {
        if (minsttance == null) {
            minsttance = new RetrofitClient();
        }
        return minsttance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
