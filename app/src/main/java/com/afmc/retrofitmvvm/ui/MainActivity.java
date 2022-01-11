package com.afmc.retrofitmvvm.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.afmc.retrofitmvvm.R;
import com.afmc.retrofitmvvm.databinding.ActivityMainBinding;
import com.afmc.retrofitmvvm.ui.adapter.PostsAdapter;
import com.afmc.retrofitmvvm.ui.pojo.PostModel;
import com.afmc.retrofitmvvm.ui.viewmodel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostsAdapter postsAdapter;
    ActivityMainBinding activityMainBinding;
    Boolean progressVisibilty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setProgressVisibilty(progressVisibilty);
        activityMainBinding.setLifecycleOwner(this);
        postsAdapter = new PostsAdapter();
        activityMainBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recycler.setAdapter(postsAdapter);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getData();
        postViewModel.errorLiveData.observe(this, error -> {
                    progressVisibilty = false;
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

                }

        );
        postViewModel.mutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                progressVisibilty = false;
                postsAdapter.setList(postModels);
            }
        });



    }
}