package com.simplx.apps.pockemonapp.ui.favorite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simplx.apps.pockemonapp.R;
import com.simplx.apps.pockemonapp.model.PokemonViewModel;
import com.simplx.apps.pockemonapp.pojo.Pokemon;
import com.simplx.apps.pockemonapp.ui.main.MainActivity;
import com.simplx.apps.pockemonapp.ui.main.PokemonAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    PokemonAdapter adapter;
    RecyclerView recyclerView;
    PokemonViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        adapter = new PokemonAdapter(this);
        recyclerView = findViewById(R.id.pokemon_fav_recyclerView);
        recyclerView.setAdapter(adapter);

        setUpSwip();

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getFavPokemon();

        viewModel.getFav_list().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                adapter.setList((ArrayList<Pokemon>) pokemons);
            }
        });

        Button add = findViewById(R.id.go_to_home_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            }
        });
    }

    private void setUpSwip() {

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = adapter.getPokemonByPosition(position);
                viewModel.delete(pokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoriteActivity.this, "deleted to database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}