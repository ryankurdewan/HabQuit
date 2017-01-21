package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.AchievementAdapter;
import com.aquamorph.habquit.model.Achievement;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 1/17/2017.
 */

//Achievement class page for listing achievements
//won by user.


public class Achievements extends AppCompatActivity {

    /* The @Bind is part of the butterknife from Jake Horton, It allows
    for allows for more convenince and cuts out boiler plate code.
    In this class the lines of code no longer needed will have a comment at that line

    @BindView(R.id.achievements) RecyclerView mRecyclerView;
    ButterKnife.bind(this);   */

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.achievements);

        recyclerView = (RecyclerView) findViewById(R.id.achievements);

        //This is for testing of data. Later this will call rest service for data.

        ArrayList<Achievement> achievements = new ArrayList<>();
        for(int i=0;i<10;i++){
            Achievement achievements1 = new Achievement();
            achievements1.setTitle("Title" + i);
            achievements1.setMessage("Message" + 1);
            achievements1.setPoints(i);
            achievements.add(achievements1);

        }

        //This instantiates the adapter and sets the data.

        AchievementAdapter achievementAdapter = new AchievementAdapter(achievements);
          recyclerView.setAdapter(achievementAdapter);
          recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}



 /* Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Achievements.this,"I have been clicked",Toast.LENGTH_LONG).show();
            }
        }); */

 /* @OnClick(R.id.button)
    public void onButtonClick(){
        Toast.makeText(Achievements.this,"Me too",Toast.LENGTH_LONG).show();
    } */
