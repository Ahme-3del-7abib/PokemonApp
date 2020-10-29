package com.simplx.apps.pockemonapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.simplx.apps.pockemonapp.R;
import com.simplx.apps.pockemonapp.pojo.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> {


    private ArrayList<Pokemon> mList = new ArrayList<>();
    private Context mContext;

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PokemonHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {

        holder.pokemonName.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getUrl())
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<Pokemon> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public Pokemon getPokemonByPosition(int position) {
        return mList.get(position);
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {

        private ImageView pokemonImage;
        private TextView pokemonName;

        public PokemonHolder(@NonNull View itemView) {
            super(itemView);

            pokemonImage = itemView.findViewById(R.id.pokemon_imageView);
            pokemonName = itemView.findViewById(R.id.pokemon_name_textView);
        }
    }
}
