package adam.pokemon.myapp.viewmodel

import adam.pokemon.myapp.api.pokemonlist.model.ApiResponsePokemonList
import adam.pokemon.myapp.api.pokemonlist.repository.PokemonRepository
import adam.pokemon.myapp.utils.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    val pokemonData: LiveData<Response<ApiResponsePokemonList>>
        get() = pokemonRepository.pokemonData

    init {
        viewModelScope.launch {
            pokemonRepository.getPokemon()
        }
    }
}