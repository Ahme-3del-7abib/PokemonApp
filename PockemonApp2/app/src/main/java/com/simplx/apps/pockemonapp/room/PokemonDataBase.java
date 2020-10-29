package com.simplx.apps.pockemonapp.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.simplx.apps.pockemonapp.pojo.Pokemon;

@Database(entities = Pokemon.class, version = 1, exportSchema = false)
public abstract class PokemonDataBase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();


}
