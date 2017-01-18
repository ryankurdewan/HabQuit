package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aquamorph.habquit.R;

public class Login extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		Button loginButton = (Button) findViewById(R.id.registerButton);

		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openRegister();
			}
		});
	}

	/**
	 * openRegister() starts register activity
	 */
	public void openRegister() {
		startActivity(new Intent(this, Register.class));
	}
}
