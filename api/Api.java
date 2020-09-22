package com.example.sample.api;

import com.example.sample.Resource;
import com.example.sample.model.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Api {

    @GET("posts")
    Flowable<List<User>> getUserDetails();
}
