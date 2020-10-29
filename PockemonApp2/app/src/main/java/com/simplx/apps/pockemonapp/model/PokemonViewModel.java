package com.simplx.apps.pockemonapp.model;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simplx.apps.pockemonapp.pojo.Pokemon;
import com.simplx.apps.pockemonapp.pojo.PokemonResponse;
import com.simplx.apps.pockemonapp.repo.PokemonRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    private static final String TAG = "PokemonViewModel";
    private PokemonRepository repository;
    MutableLiveData<ArrayList<Pokemon>> data = new MutableLiveData<>();
    private LiveData<List<Pokemon>> fav_list = null;

    @ViewModelInject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getData() {
        return data;
    }

    public void getPokemon() {
        repository.getPokemonResponse()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Pokemon> list = pokemonResponse.getResult();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"
                                    + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> data.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }

    public void insert(Pokemon pokemon) {
        repository.insert(pokemon);
    }

    public void delete(String name) {
        repository.delete(name);
    }

    public void getFavPokemon() {
        fav_list = repository.getAllPokemon();
    }

    public LiveData<List<Pokemon>> getFav_list() {
        return fav_list;
    }
}
