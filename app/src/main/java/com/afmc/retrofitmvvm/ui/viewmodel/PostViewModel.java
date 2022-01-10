package com.afmc.retrofitmvvm.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.afmc.retrofitmvvm.ui.pojo.PostModel;
import com.afmc.retrofitmvvm.ui.repo.CallingAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    public MutableLiveData<List<PostModel>> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    CallingAPI callingAPI = new CallingAPI();
    public void getData(){
        callingAPI.callAPI().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                     errorLiveData.setValue(t.toString());
            }
        });
    }
}
