package com.afmc.retrofitmvvm.ui.repo;

import com.afmc.retrofitmvvm.retrofit_client.RetrofitClient;
import com.afmc.retrofitmvvm.retrofit_client.RetrofitInterface;
import com.afmc.retrofitmvvm.ui.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CallingAPI {
    Retrofit retrofit;

    public Call<List<PostModel>> callAPI() {
        retrofit = RetrofitClient.getRetrofitClient();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        return retrofitInterface.getData();
    }
}
