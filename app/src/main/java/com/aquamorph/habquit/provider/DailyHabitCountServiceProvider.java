package com.aquamorph.habquit.provider;

import android.content.Context;

import com.aquamorph.habquit.model.DailyCountHabit;
import com.aquamorph.habquit.model.DailyCounts;
import com.aquamorph.habquit.service.DailyHabitCountService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shawnkelly on 3/26/17.
 */

public class DailyHabitCountServiceProvider {

    DailyHabitCountService dailyHabitCountService;
    Context context;
    int id = getUserId();
    int habitId = getHabitId();
    int daysBack = getDaysBack();

    public DailyHabitCountService getDailyHabitCountService() {
        return dailyHabitCountService;
    }

    public void setDailyHabitCountService(DailyHabitCountService dailyHabitCountService) {
        this.dailyHabitCountService = dailyHabitCountService;
    }

    public int getUserId() {
        return 1;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return 1;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getDaysBack() {
        return 30;
    }

    public void setDaysBack(int daysBack) {
        this.daysBack = daysBack;
    }

    public DailyHabitCountServiceProvider(Context context) {
        dailyHabitCountService = new Retrofit.Builder()
                .baseUrl("http://habquit.azurewebsites.net/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(DailyHabitCountService.class);
        this.context = context;
    }
    public void getDailyHabitCounts(final DailyHabitCountService.OnDailyHabitCountListener listener) {
        Call<DailyCountHabit> call = dailyHabitCountService.getDailyCounts(id, habitId, daysBack);
        String str = "";
        call.enqueue(new Callback<DailyCountHabit>() {
            @Override
            public void onResponse(Call<DailyCountHabit> call, Response<DailyCountHabit> response) {
                DailyCountHabit dailyCounts = response.body();
                listener.onSuccess(dailyCounts);
            }

            @Override
            public void onFailure(Call<DailyCountHabit> call, Throwable t) {

            }
        });

    }
}
