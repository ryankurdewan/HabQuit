package com.aquamorph.habquit.service;


import com.aquamorph.habquit.model.Achievement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shawn on 1/21/2017.
 */

public interface AchievementService {

    /**
     * this maps a service implementation (created in the constructor of AchievementServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */
    @GET ("/api/user_reg")
    Call<List<Achievement>> getAchievements();

    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  AchievemntActivity implents this
     * so AchievementServiceProvider knows what properties are available
     */
    interface OnAchievementListener {
        void onSuccess(List<Achievement> achievements);
        void onError();
    }
}
