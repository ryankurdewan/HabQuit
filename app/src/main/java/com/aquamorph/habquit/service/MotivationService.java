package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.Motivation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shawn on 2/7/2017.
 */

public interface MotivationService {

	/**
	 * this maps a service implementation (created in the constructor of MotivationServiceProvider)
	 * to the correct endpoint on a remote server
	 * @return
	 */

	@GET("api/motivation")
	Call<Motivation> getMotivation();

	/**
	 * this is an interface designed to allow communication between activity and service provider
	 * it is used to promote loose coupling and reuse of services.  MotivationActivity implents this
	 * so MotivationServiceProvider knows what properties are available
	 */
	interface OnMotivationListener {
		void onSuccess(Motivation motivation);
		void onError();
	}
}
