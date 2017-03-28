package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ryankurdewan on 3/27/17.
 */

public class DeleteRecordId {
    @Expose
    @SerializedName("user_id")
    int userId;
    @Expose
    @SerializedName("habit_id")
    int habitId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }
}
