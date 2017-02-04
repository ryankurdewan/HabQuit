package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Shawn on 2/3/2017.
 */

public class HabitSgk {
    @Expose
    @SerializedName("habit_id")
    private int habitId;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    private List<AchieveList> achieveLists;
    @Expose
    private List<Motivation> motivations;
    @Expose
    private List<TrackHabit> trackHabits;

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AchieveList> getAchieveLists() {
        return achieveLists;
    }

    public void setAchieveLists(List<AchieveList> achieveLists) {
        this.achieveLists = achieveLists;
    }

    public List<Motivation> getMotivations() {
        return motivations;
    }

    public void setMotivations(List<Motivation> motivations) {
        this.motivations = motivations;
    }

    public List<TrackHabit> getTrackHabits() {
        return trackHabits;
    }

    public void setTrackHabits(List<TrackHabit> trackHabits) {
        this.trackHabits = trackHabits;
    }
}
