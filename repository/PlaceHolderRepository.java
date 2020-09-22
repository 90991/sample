package com.example.sample.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.sample.Resource;
import com.example.sample.api.Api;
import com.example.sample.client.RetrofitClient;
import com.example.sample.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PlaceHolderRepository {

    private static PlaceHolderRepository repository = null;

    private static RetrofitClient retrofitClient = null;
    private Api api;

    public PlaceHolderRepository() {
        if (retrofitClient == null) {
            retrofitClient = RetrofitClient.getInstance();
        }
        api = retrofitClient.getApi();
    }

    public static PlaceHolderRepository getInstance() {
        if (repository == null) {
            repository = new PlaceHolderRepository();
        }
        return repository;
    }

    public LiveData<Resource<List<User>>> getUser() {

        final MediatorLiveData<Resource<List<User>>> details = new MediatorLiveData<>();
        details.postValue(Resource.loading((List<User>) null));

        final LiveData<Resource<List<User>>> source = LiveDataReactiveStreams.fromPublisher(

                api.getUserDetails()
                        .onErrorReturn(new Function<Throwable, List<User>>() {
                            @Override
                            public List<User> apply(Throwable throwable) throws Exception {
                                User user = new User();
                                user.setId(-1);
                                List<User> list = new ArrayList<>();
                                list.add(user);
                                return list;
                            }
                        })
                        .map(new Function<List<User>, Resource<List<User>>>() {
                            @Override
                            public Resource<List<User>> apply(List<User> users) throws Exception {
                                if (users.size() > 0) {
                                    if (users.get(0).getId() == -1) {
                                        return Resource.error("Something went wrong", null);
                                    }
                                }
                                return Resource.success(users);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        details.addSource(source, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {
                details.postValue(listResource);
                details.removeSource(source);
            }
        });
        return details;
    }
}
