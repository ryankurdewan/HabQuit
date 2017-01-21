package com.aquamorph.habquit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Achievement;

import java.util.List;

/**
 * Created by shawnkelly on 1/20/17.
 */

//The adapter takes the data and binds it to the recyclerview.

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder>{

    private List<Achievement> achievements;

    public AchievementAdapter(List<Achievement> achievements){
        this.achievements = achievements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_achievement,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Achievement achievement = achievements.get(position);
        if(achievement != null){
            //load view holder
            holder.title.setText(achievement.getTitle());
            holder.points.setText(String.valueOf(achievement.getPoints()));
            holder.message.setText(achievement.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return achievements != null ? achievements.size() : 0;
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView message;
        TextView points;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            message = (TextView) v.findViewById(R.id.message);
            points = (TextView) v.findViewById(R.id.points);
        }
    }
}
