package com.aquamorph.habquit.provider;

import android.util.Log;

import com.aquamorph.habquit.model.HabitSgk;
import com.aquamorph.habquit.service.HabitSgkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/3/2017.
 */

public class HabitSgkServiceProvider {
    HabitSgkService habitSgkService;

    /**
     * implementation of service is created here.
     * base url for service is defined here
     * basic service configuration is done here
     */
    public HabitSgkServiceProvider(){
        habitSgkService = new Retrofit.Builder()
        .baseUrl("http://habquit.azurewebsites.net/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(HabitSgkService.class);
    }

    /**
     * dispatches a GET request to remote server and informs listener of response.  If successful,
     * a List of Achievement is returned to listener.
     * @param listener
     */
      public void getHabitSgks (final HabitSgkService.OnHabitSgkListener listener) {
          Call<List<HabitSgk>> call = habitSgkService.getHabitSgk();
          call.enqueue(new Callback<List<HabitSgk>>() {
              @Override
              public void onResponse(Call<List<HabitSgk>> call, Response<List<HabitSgk>> response) {
                  List<HabitSgk> habitSgks = response.body();
                  listener.onSuccess(habitSgks);
              }

              @Override
              public void onFailure(Call<List<HabitSgk>> call, Throwable t) {
                  Log.d("HabitService Fail", t.getMessage());
                  listener.onError();
              }
          });
      }
}
