package com.simplx.apps.pockemonapp.di;

import android.app.Application;

import androidx.room.Room;

import com.simplx.apps.pockemonapp.room.PokemonDao;
import com.simplx.apps.pockemonapp.room.PokemonDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomModule {

    @Provides
    @Singleton
    public static PokemonDataBase provideDataBase(Application application) {

        return Room.databaseBuilder(application, PokemonDataBase.class, "Favorite_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao pokemonDao(PokemonDataBase pokemonDataBase) {
        return pokemonDataBase.pokemonDao();
    }
}
