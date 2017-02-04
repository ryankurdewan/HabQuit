package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
/**
 * Created by Shawn on 2/3/2017.
 */

public class UserAchievement {
    @Expose
    @SerializedName("user_id")
    private int userId;
    @Expose
    @SerializedName("achievement_id")
    private int achievementId;
    @Expose
    @SerializedName("user_achieve")
    private int userAchieve;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getUserAchieve() {
        return userAchieve;
    }

    public void setUserAchieve(int userAchieve) {
        this.userAchieve = userAchieve;
    }
}

