package com.aquamorph.habquit.provider;

import android.util.Log;


import com.aquamorph.habquit.model.UserReg;
import com.aquamorph.habquit.service.AchievementService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 1/21/2017.
 */

public class AchievementServiceProvider {
    AchievementService achievementService;

    /**
     * implementation of service is created here.
     * base url for service is defined here
     * basic service configuration is done here
     */
    public AchievementServiceProvider(){
        achievementService = new Retrofit.Builder()
        .baseUrl("http://habquit.azurewebsites.net/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AchievementService.class);
    }

    /**
     * dispatches a GET request to remote server and informs listener of response.  If successful,
     * a List of Achievement is returned to listener.
     * @param listener
     */
    public void  getUserRegs (final AchievementService.OnAchievementListener listener){
        Call<UserReg> call = achievementService.getUserReg(1); //THis is where we'll user id
        call.enqueue(new Callback<UserReg>() {
            @Override
            public void onResponse(Call<UserReg> call, Response<UserReg> response) {
                UserReg userReg = response.body();
                listener.onSuccess(userReg);
            }

            @Override
            public void onFailure(Call<UserReg> call, Throwable t) {
                Log.d("Shit",t.getMessage());
                listener.onError();
            }
        });
    }
}
