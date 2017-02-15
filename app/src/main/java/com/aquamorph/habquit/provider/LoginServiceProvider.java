package com.aquamorph.habquit.provider;

/**
 * Created by Shawn on 2/4/2017.
 */
public class LoginServiceProvider {
    private static LoginServiceProvider ourInstance = new LoginServiceProvider();

    public static LoginServiceProvider getInstance() {
        return ourInstance;
    }

    private LoginServiceProvider() {
    }

    public Integer getUserId(){
        return 1;//this is shawn for now
    }
}
