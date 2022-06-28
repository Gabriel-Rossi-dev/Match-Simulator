package com.example.simulator.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulator.ui.adapter.MatchesAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.simulator.R;
import com.example.simulator.data.MatchesAPI;
import com.example.simulator.databinding.ActivityMainBinding;
import com.example.simulator.domain.Match;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesAPI matchesApi;
    private MatchesAdapter matchesAdapter = new MatchesAdapter(Collections.emptyList());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatActionButton();

    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gabriel-rossi-dev.github.io/Match-Simulator-Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(MatchesAPI.class);
    }

    private void setupMatchesList() {
        binding.rvMatch.setHasFixedSize(true);
        binding.rvMatch.setLayoutManager( new LinearLayoutManager(this));
        binding.rvMatch.setAdapter(matchesAdapter);
        findMatchesFromApi();

    }

    private void setupMatchesRefresh() {
        binding.srlMatch.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void setupFloatActionButton() {
        binding.fabRandomMatch.setOnClickListener(view -> {

            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Random random = new Random();
                    for (int i = 0; i < matchesAdapter.getItemCount(); i++) {
                        Match match = matchesAdapter.getMatches().get(i);
                        match.getHomeTeam().setScore(random.nextInt(match.getHomeTeam().getStars() + 1));
                        match.getAwayTeam().setScore(random.nextInt(match.getAwayTeam().getStars() + 1));
                        matchesAdapter.notifyItemChanged(i);
                    }
                }
            });
        });
    }




    private void findMatchesFromApi() {
        binding.srlMatch.setRefreshing(true);
        matchesApi.getMatches().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()){
                    List<Match> matches = response.body();
                    matchesAdapter = new MatchesAdapter(matches);
                    binding.rvMatch.setAdapter(matchesAdapter);
                }else {
                    showErrorMessage();
                }
                binding.srlMatch.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                showErrorMessage();
                binding.srlMatch.setRefreshing(false);
            }
        });
    }





    private void showErrorMessage() {
        Snackbar.make(binding.fabRandomMatch, R.string.api_error, Snackbar.LENGTH_LONG).show();
    }
}