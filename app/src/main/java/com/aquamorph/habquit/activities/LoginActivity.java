package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
		GoogleApiClient.OnConnectionFailedListener {

	private final String TAG = "LoginActivity";
	private LinearLayout profileSection;
	private Button signOut;
	private SignInButton signIn;
	private TextView nameTextView, emailTextView, givenNameTextView, familyNameTextView, idTextView;
	private ImageView profilePicture;
	private GoogleApiClient googleApiClient;
	private static final int REQ_CODE = 9001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		profileSection = (LinearLayout) findViewById(R.id.profile_section);
		signOut = (Button) findViewById(R.id.user_logout);
		signIn = (SignInButton) findViewById(R.id.sign_in_button);
		nameTextView = (TextView) findViewById(R.id.user_name);
		emailTextView = (TextView) findViewById(R.id.user_email);
		givenNameTextView = (TextView) findViewById(R.id.user_given_name);
		familyNameTextView = (TextView) findViewById(R.id.user_family_name);
		idTextView = (TextView) findViewById(R.id.user_id);
		profilePicture = (ImageView) findViewById(R.id.profile_picture);
		signIn.setOnClickListener(this);
		signOut.setOnClickListener(this);
		GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestEmail().build();
		googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi
				(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
		updateUI(false);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sign_in_button:
				signIn();
				break;
			case R.id.user_logout:
				signOut();
				break;
		}
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}

	private void signIn() {
		Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
		startActivityForResult(intent, REQ_CODE);

	}

	private void signOut() {
		Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
			@Override
			public void onResult(@NonNull Status status) {
				updateUI(false);
			}
		});
	}

	private void handleResult(GoogleSignInResult result) {
		if(result.isSuccess()) {
			GoogleSignInAccount account = result.getSignInAccount();
			nameTextView.setText(account.getDisplayName());
			emailTextView.setText(account.getEmail());
			givenNameTextView.setText(account.getGivenName());
			familyNameTextView.setText(account.getFamilyName());
			idTextView.setText(account.getId());
			Glide.with(this).load(account.getPhotoUrl().toString()).into(profilePicture);
			updateUI(true);
		}
		else updateUI(false);
	}

	private void updateUI(Boolean isLogin) {
		if(isLogin) {
			profileSection.setVisibility(View.VISIBLE);
			signIn.setVisibility(View.GONE);
		} else {
			profileSection.setVisibility(View.GONE);
			signIn.setVisibility(View.VISIBLE);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQ_CODE) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			handleResult(result);
		}
	}
}
