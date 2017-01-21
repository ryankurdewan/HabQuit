package com.aquamorph.habquit.model;

/**
 * Created by shawnkelly on 1/20/17.
 */

public class Achievement {
    private String title;
    private int points;
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
