package com.simplx.apps.pockemonapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.simplx.apps.pockemonapp.R;
import com.simplx.apps.pockemonapp.pojo.Pokemon;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    PokemonAdapter adapter;
    RecyclerView recyclerView;
    PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new PokemonAdapter(this);
        recyclerView = findViewById(R.id.pokemon_recyclerView);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getPokemon();

        viewModel.data.observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                adapter.setList(pokemons);
            }
        });
    }
}