package com.example.sample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.Resource;
import com.example.sample.model.User;
import com.example.sample.repository.PlaceHolderRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private PlaceHolderRepository repository;

    private LiveData<Resource<List<User>>> userDetails;

    public void inititate() {
        repository = PlaceHolderRepository.getInstance();
    }

    public void getUserDetails() {
        userDetails = repository.getUser();

    }

    public LiveData<Resource<List<User>>> observeUser() {
        return userDetails;
    }
}
