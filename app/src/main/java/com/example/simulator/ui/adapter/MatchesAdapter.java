package com.example.simulator.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simulator.databinding.MatchItemBinding;
import com.example.simulator.domain.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Match match = matches.get(position);


        //Addapta os dados da partida (Recuperada da API) para o nosso layout
        Glide.with(context).load(match.getHomeTeam().getImage()).circleCrop().into(holder.binding.ivHomeTeamFlag);
        holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        if (match.getHomeTeam().getScore() != null) {
            holder.binding.tvGolsHomeTeam.setText(String.valueOf(match.getHomeTeam().getScore()));
        }
        Glide.with(context).load(match.getAwayTeam().getImage()).circleCrop().into(holder.binding.ivAwayTeamFlag);
        holder.binding.tvAwayTeamName.setText(match.getAwayTeam().getName());
        if (match.getAwayTeam().getScore() != null) {
            holder.binding.tvGolsAwayTeam.setText(String.valueOf(match.getAwayTeam().getScore()));
        }


    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
