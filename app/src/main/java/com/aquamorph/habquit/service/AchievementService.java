package com.aquamorph.habquit.service;


import com.aquamorph.habquit.model.Achievement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shawn on 1/21/2017.
 */

public interface AchievementService {
    @GET ("/api/values")
    public Call<List<Achievement>> getAchievements();

    interface OnAchievementListener {
        void onSuccess(List<Achievement> achievements);
        void onError();
    }
}
