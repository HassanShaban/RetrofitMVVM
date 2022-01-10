package com.afmc.retrofitmvvm.retrofit_client;

import com.afmc.retrofitmvvm.ui.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<PostModel>> getData();

}
