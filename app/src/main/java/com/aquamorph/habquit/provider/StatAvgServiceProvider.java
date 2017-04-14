package com.aquamorph.habquit.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;

import com.aquamorph.habquit.model.StatAvg;
import com.aquamorph.habquit.service.StatAvgService;
import com.aquamorph.habquit.utils.HabitParameter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shawnkelly on 3/25/17.
 */

public class StatAvgServiceProvider {



    StatAvgService statAvgService;
    Context context;
    int id = getUserId() ;
    int habitId= getHabitId();
    int daysback = getDaysBack();

    private int getDaysBack() {
        return 30;
    }

    private int getHabitId() {
        return 1;
    }

    private int getUserId() {
        return 1;
    }
    public StatAvgServiceProvider(Context context){
        statAvgService = new Retrofit.Builder()
        .baseUrl("http://habquit.azurewebsites.net/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(StatAvgService.class);
        this.context = context;
    }

    public void getStatAvg(final StatAvgService.OnStatAvgListener listener) {
        Call<StatAvg> call = statAvgService.getStatAvg(id, habitId, daysback );
        String str = "";
        call.enqueue(new Callback<StatAvg>() {
            @Override
            public void onResponse(Call<StatAvg> call, Response<StatAvg> response) {
                StatAvg statAvg = response.body();
                listener.onSuccess(statAvg);


            }

            @Override
            public void onFailure(Call<StatAvg> call, Throwable t) {

            }
        });
    }


}
