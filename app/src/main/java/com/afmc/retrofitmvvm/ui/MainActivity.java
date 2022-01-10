package com.afmc.retrofitmvvm.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afmc.retrofitmvvm.R;
import com.afmc.retrofitmvvm.ui.adapter.PostsAdapter;
import com.afmc.retrofitmvvm.ui.pojo.PostModel;
import com.afmc.retrofitmvvm.ui.viewmodel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostsAdapter postsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        postsAdapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postsAdapter);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getData();
        postViewModel.mutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                postsAdapter.setList(postModels);
            }
        });
        postViewModel.errorLiveData.observe(this, error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }

        );

    }
}