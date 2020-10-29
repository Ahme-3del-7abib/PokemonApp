package com.simplx.apps.pockemonapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.simplx.apps.pockemonapp.pojo.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    void insert(Pokemon pokemon);

    @Query("DELETE FROM favorite_table WHERE name = :name")
    void delete(String name);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Pokemon>> getAllPokemons();

}
