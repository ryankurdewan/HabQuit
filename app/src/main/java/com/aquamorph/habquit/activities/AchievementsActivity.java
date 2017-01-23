package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.AchievementAdapter;
import com.aquamorph.habquit.model.Achievement;
import com.aquamorph.habquit.provider.AchievementServiceProvider;
import com.aquamorph.habquit.service.AchievementService;
import com.aquamorph.habquit.activities.MainActivity;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 1/17/2017.
 */

//Achievement class page for listing achievements
//won by user.


public class AchievementsActivity extends AppCompatActivity implements AchievementService.OnAchievementListener {

    /* The @Bind is part of the butterknife from Jake Horton, It allows
    for allows for more convenience and cuts out boiler plate code.
    In this class the lines of code no longer needed will have a comment at that line

    @BindView(R.id.achievements) RecyclerView mRecyclerView;
    ButterKnife.bind(this);   */

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.achievements);

        recyclerView = (RecyclerView) findViewById(R.id.achievements);
        getAchievement();
        /* This is for testing of data. Later this will call rest service for data.

        ArrayList<Achievement> achievements = new ArrayList<>();
        for(int i=0;i<10;i++){
            Achievement achievements1 = new Achievement();
            achievements1.setAchievementTitle("Title" + i);
            achievements1.setMessage("Message" + i);
            achievements1.setPoints(i);
            achievements.add(achievements1);
        } */

        //This instantiates the adapter and sets the data.
    }


    /**
     *   Calls achievementServiceProvider passing reference of self (implements OnAchivementListener)
     *   to communicate properly with service
     */
    private void getAchievement(){
        AchievementServiceProvider achievementServiceProvider = new AchievementServiceProvider();
        achievementServiceProvider.getAchievements(this);
    }

    /**
     * this function is called when the achievementServiceProvider successfully makes a service call
     * to get a list of achievements from remote server (http status 200)
     * @param achievements
     */
    @Override
    public void onSuccess(List<Achievement> achievements) {
        AchievementAdapter achievementAdapter = new AchievementAdapter(achievements);
        recyclerView.setAdapter(achievementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onError() {

    }


    //Creates and sets options menu

   public void openGraphDisplay(){
       startActivity(new Intent(this, GraphDisplay.class));
   }

   public void openMotivation(){
      // startActivity(new Intent(this, Motivation.class));
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.achievement_drop_down_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.graphDisplay:
                openGraphDisplay();
                break;
            case R.id.motivation:
                openMotivation();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
 /* Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AchievementsActivity.this,"I have been clicked",Toast.LENGTH_LONG).show();
            }
        }); */

 /* @OnClick(R.id.button)
    public void onButtonClick(){
        Toast.makeText(AchievementsActivity.this,"Me too",Toast.LENGTH_LONG).show();
    } */


