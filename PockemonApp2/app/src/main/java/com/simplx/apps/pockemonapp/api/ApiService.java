package com.simplx.apps.pockemonapp.api;


import com.simplx.apps.pockemonapp.pojo.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemon();
}
