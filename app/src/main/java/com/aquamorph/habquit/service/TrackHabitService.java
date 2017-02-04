package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.TrackHabit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shawn on 2/4/2017.
 */

public interface TrackHabitService {

    /**
     * this maps a service implementation (created in the constructor of TrackHabitServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */

    @GET("/api/track_habits")
    Call<List<TrackHabit>> getTrackHabit();

    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  TrackHabitActivity implents this
     * so TrackHabitServiceProvider knows what properties are available
     */

    interface OnTrackHabitListener {
        void onSuccess(List<TrackHabit> trackHabits);
        void onError();
    }

}
