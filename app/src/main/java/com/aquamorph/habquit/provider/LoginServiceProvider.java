package com.aquamorph.habquit.provider;

import android.util.Log;

import com.aquamorph.habquit.service.LoginService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/4/2017.
 */
public class LoginServiceProvider {
    private LoginService loginService;

    private static final String TAG = "LoginServiceProvider";

//    private static LoginServiceProvider ourInstance = new LoginServiceProvider();
//
//    public static LoginServiceProvider getInstance() {
//        return ourInstance;
//    }

    public Integer getUserId(){
        return 1;//this is shawn for now
    }

//    private LoginServiceProvider() {
//
//    }

    public LoginServiceProvider() {
        loginService = new Retrofit.Builder()
                .baseUrl("http://habquit.azurewebsites.net/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(LoginService.class);
    }

    public void postLoginInfo(double googleID, String userName, String email) {
        Call<ResponseBody> call = loginService.postLoginInfo(googleID, userName, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, call.request().body().toString());
                if (response.isSuccessful()) {
                    Log.i(TAG, "Response Successful");
                    Log.i(TAG, String.valueOf(response.body()));
                } else {
                    Log.i(TAG, "Bad Response Code!");
                    Log.i(TAG, String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "Failed to call to REST API successfully");
            }
        });
    }
}
