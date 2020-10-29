package com.simplx.apps.pockemonapp.repo;

import androidx.lifecycle.LiveData;

import com.simplx.apps.pockemonapp.api.ApiService;
import com.simplx.apps.pockemonapp.pojo.Pokemon;
import com.simplx.apps.pockemonapp.pojo.PokemonResponse;
import com.simplx.apps.pockemonapp.room.PokemonDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepository {

    private ApiService apiService;
    private PokemonDao pokemonDao;


    @Inject
    public PokemonRepository(ApiService apiService, PokemonDao pokemonDao) {
        this.apiService = apiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemonResponse() {
        return apiService.getPokemon();
    }

    public void insert(Pokemon pokemon) {
        pokemonDao.insert(pokemon);
    }

    public void delete(String name) {
        pokemonDao.delete(name);
    }

    public LiveData<List<Pokemon>> getAllPokemon() {
        return pokemonDao.getAllPokemons();
    }

}
