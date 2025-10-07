package adam.pokemon.myapp.viewmodel

import adam.pokemon.myapp.api.pokemonlist.model.Ability
import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import adam.pokemon.myapp.api.pokemonlist.model.PokemonAbility
import adam.pokemon.myapp.api.pokemonlist.model.PokemonList
import adam.pokemon.myapp.api.pokemonlist.repository.PokemonRepository
import adam.pokemon.myapp.utils.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    val pokemonData: LiveData<Response<ApiResponsePokemonList>>
        get() = pokemonRepository.pokemonData

    private val favouriteSet = mutableSetOf<PokemonList>()
    private val _favourites = MutableLiveData<List<PokemonList>>()
    val favourites: LiveData<List<PokemonList>> get() = _favourites


    val pokemonDetail: LiveData<Response<PokemonAbility>>
        get() = pokemonRepository.pokemonDetail


    init {
        viewModelScope.launch {
            pokemonRepository.getPokemon()
        }
    }

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDetail(name)
        }
    }

    fun toggleFavourite(pokemon: PokemonList) {
        if (favouriteSet.contains(pokemon)) {
            favouriteSet.remove(pokemon)
        } else {
            favouriteSet.add(pokemon)
        }
        _favourites.value = favouriteSet.toList()
    }


    fun isFavourite(pokemon: PokemonList): Boolean {
        return favouriteSet.contains(pokemon)
    }


}