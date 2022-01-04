package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.space.myapplication.core.Abstract

interface PokemonCommunication : Abstract.Mapper {
    fun map(pokemon: List<PokemonUi>)

    fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>)

    class Base : PokemonCommunication {
        private val listLiveData = MutableLiveData<List<PokemonUi>>()
        private val listPokemon = mutableListOf<PokemonUi>()

        override fun map(pokemon: List<PokemonUi>) {
            if (pokemon.firstOrNull() is PokemonUi.Base) {
                listPokemon.addAll(pokemon)
                listLiveData.value = listPokemon
            } else {
                listLiveData.value = pokemon
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>) {
            listLiveData.observe(owner, observer)
        }
    }
}