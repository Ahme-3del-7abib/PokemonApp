package com.simplx.apps.pockemonapp.repo;

import com.simplx.apps.pockemonapp.api.ApiService;
import com.simplx.apps.pockemonapp.pojo.PokemonResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepository {

    private ApiService apiService;

    @Inject
    public PokemonRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<PokemonResponse> getPokemonResponse() {
        return apiService.getPokemon();
    }
}
