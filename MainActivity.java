package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.sample.adapter.UserAdapter;
import com.example.sample.databinding.ActivityMainBinding;
import com.example.sample.model.User;
import com.example.sample.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        adapter = new UserAdapter();
        viewModel.inititate();


        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setItemAnimator(new DefaultItemAnimator());

        viewModel.getUserDetails();
        subscribeUser();

    }

    private void subscribeUser() {
        viewModel.observeUser().observe(this, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {

                switch (listResource.status) {
                    case SUCCESS:
                        adapter.setData(listResource.data);
                        Log.d(TAG, "onChanged: SUCCESS ");
                        break;
                    case ERROR:
                        Log.d(TAG, "onChanged: ERROR");
                        break;
                    case LOADING:
                        Log.d(TAG, "onChanged: LOADING");
                        break;

                }
            }
        });
    }
}