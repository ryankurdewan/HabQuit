package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.HabitSgk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shawn on 2/3/2017.
 */

public interface HabitSgkService {

    /**
     * this maps a service implementation (created in the constructor of HabitSgkServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */
    @GET ("/api/habits/")
    Call<List<HabitSgk>> getHabitSgk();


    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  AchievemntActivity implents this
     * so HabitSgkServiceProvider knows what properties are available
     */
    interface OnHabitSgkListener {
        void onSuccess(List<HabitSgk> habitSgks);
        void onError();
    }
}
